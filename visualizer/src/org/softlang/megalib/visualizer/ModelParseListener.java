/*
 *  All rights reserved.
 */
package org.softlang.megalib.visualizer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import main.antlr.techdocgrammar.MegalibBaseListener;
import main.antlr.techdocgrammar.MegalibParser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.java.megalib.checker.services.Substitution;
import org.java.megalib.models.MegaModel;

/**
 *
 * @author Dmitri Nikonov <dnikonov at uni-koblenz.de>
 */
public class ModelParseListener extends MegalibBaseListener {

    private MegaModel model;

    private Map<String, Set<String>> substByGroup;

    public ModelParseListener() {
        model = new MegaModel();
    }

    public ModelParseListener(MegaModel m) {
        model = m;
    }

    @Override
    public void enterSubstitution(MegalibParser.SubstitutionContext ctx) {
        String subject = ctx.getChild(0).getText();
        String object = ctx.getChild(2).getText();
        Set<String> set;
        if (substByGroup.containsKey(object)) {
            set = substByGroup.get(object);
        } else {
            set = new HashSet<>();
        }
        set.add(subject);
        substByGroup.put(object, set);

    }

    @Override
    public void exitSubstitutionGroup(MegalibParser.SubstitutionGroupContext ctx) {
        model = new Substitution(model, substByGroup).substituteGroup();
        substByGroup.clear();
    }

    @Override
    public void enterSubtypeDeclaration(MegalibParser.SubtypeDeclarationContext context) {
        String derivedType = context.getChild(0).getText();
        String superType = context.getChild(2).getText();
        model.addSubtypeOf(derivedType, superType);
        if (context.getChildCount() > 4) {
            String link = context.getChild(5).getText();
            model.addLink(derivedType, link.substring(1, link.length() - 1));
        }
    }

    @Override
    public void enterInstanceDeclaration(MegalibParser.InstanceDeclarationContext context) {
        Iterator<ParseTree> it = context.children.iterator();
        String instance = it.next().getText();
        it.next(); // skip colon
        String type = it.next().getText();
        model.addInstanceOf(instance, type);
        while (it.next().getText().equals(";")) {
            String relation = it.next().getText();
            String object = it.next().getText();
            if (relation.equals("=")) {
                String link = object.substring(1, object.length() - 1);
                model.addLink(instance, link);
            } else {
                model.addRelationInstance(relation, instance, object);
            }
        }
    }

    @Override
    public void enterRelationDeclaration(MegalibParser.RelationDeclarationContext context) {
        Iterator<ParseTree> it = context.children.iterator();
        String relation = it.next().getText();
        it.next(); // skip <
        String type1 = it.next().getText();
        it.next(); // skip #
        String type2 = it.next().getText();
        model.addRelationDeclaration(relation, type1, type2);
        while (it.next().getText().equals(";")) {
            it.next(); // skip =
            String link = it.next().getText();
            model.addLink(relation, link.substring(1, link.length() - 1));
        }
    }

    @Override
    public void enterRelationInstance(MegalibParser.RelationInstanceContext context) {
        Iterator<ParseTree> it = context.children.iterator();
        String subject = it.next().getText();
        String relation = it.next().getText();
        if (relation.startsWith("^")) {
            relation = relation.substring(1);
        }
        String object = it.next().getText();
        model.addRelationInstance(relation, subject, object);

        while (it.next().getText().equals(";")) {
            relation = it.next().getText();
            if (relation.startsWith("^")) {
                relation = relation.substring(1);
            }
            object = it.next().getText();
            if (relation.equals("=")) {
                String link = object.substring(1, object.length() - 1);
                model.addLink(subject, link);
            } else {
                model.addRelationInstance(relation, subject, object);
            }
        }
    }

    @Override
    public void enterFunctionDeclaration(MegalibParser.FunctionDeclarationContext context) {
        String functionName = context.getChild(0).getText();
        List<String> parameterTypes = new ArrayList<>();
        List<String> returnTypes = new ArrayList<>();

        boolean parameter = true;
        for (int childIndex = 2; childIndex < context.getChildCount() - 1; childIndex++) {
            if (!context.getChild(childIndex).getText().equals("#") && parameter
                && !context.getChild(childIndex).getText().equals("->")) {
                parameterTypes.add(context.getChild(childIndex).getText());
            }

            if (context.getChild(childIndex).getText().equals("->")) {
                parameter = false;
            }

            if (!context.getChild(childIndex).getText().equals("#") && !parameter
                && !context.getChild(childIndex).getText().equals("->")) {
                returnTypes.add(context.getChild(childIndex).getText());
            }
        }

        model.addFunctionDeclaration(functionName, parameterTypes, returnTypes);
    }

    @Override
    public void enterFunctionInstance(MegalibParser.FunctionInstanceContext context) {
        String functionName = context.getChild(0).getText();
        List<String> outputs = new ArrayList<>();
        List<String> inputs = new ArrayList<>();

        boolean parameter = true;
        for (int childIndex = 2; childIndex < context.getChildCount() - 1; childIndex++) {
            if (!context.getChild(childIndex).getText().equals(",") && parameter
                && !context.getChild(childIndex).getText().equals("|->")
                && !context.getChild(childIndex).getText().equals("(")
                && !context.getChild(childIndex).getText().equals(")")) {
                inputs.add(context.getChild(childIndex).getText());
            }

            if (context.getChild(childIndex).getText().equals("|->")) {
                parameter = false;
            }

            if (!context.getChild(childIndex).getText().equals(",") && !parameter
                && !context.getChild(childIndex).getText().equals("|->")
                && !context.getChild(childIndex).getText().equals("(")
                && !context.getChild(childIndex).getText().equals(")")) {
                outputs.add(context.getChild(childIndex).getText());
            }
        }

        model.addFunctionApplication(functionName, inputs, outputs);
    }

    @Override
    public void enterLink(MegalibParser.LinkContext context) {
        Iterator<ParseTree> it = context.children.iterator();
        String subject = it.next().getText();
        it.next(); // skip =
        String link = it.next().getText();
        link = link.substring(1, link.length() - 1);
        model.addLink(subject, link);
        while (it.next().getText().equals(";")) {
            String relation = it.next().getText();
            String object = it.next().getText();
            if (relation.equals("=")) {
                link = object.substring(1, object.length() - 1);
                model.addLink(subject, link);
            } else {
                model.addRelationInstance(relation, subject, object);
            }
        }
    }

    public MegaModel getModel() {
        return model;
    }

}
