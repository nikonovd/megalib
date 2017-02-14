/*
 *  All rights reserved.
 */
package org.softlang.megalib.visualizer.utils;

import java.util.Objects;

/**
 *
 * @author Dmitri Nikonov <dnikonov at uni-koblenz.de>
 */
public class Tuple<L, R> {

    private L first;

    private R second;

    public Tuple(L first, R second) {
        this.first = first;
        this.second = second;
    }

    public L getFirst() {
        return first;
    }

    public R getSecond() {
        return second;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.first);
        hash = 89 * hash + Objects.hashCode(this.second);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Tuple<?, ?> other = (Tuple<?, ?>) obj;
        if (!Objects.equals(this.first, other.first)) {
            return false;
        }
        if (!Objects.equals(this.second, other.second)) {
            return false;
        }
        return true;
    }

}
