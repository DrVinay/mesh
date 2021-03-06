/**
 * ADOBE SYSTEMS INCORPORATED
 * Copyright 2009-2013 Adobe Systems Incorporated
 * All Rights Reserved.
 *
 * NOTICE: Adobe permits you to use, modify, and distribute
 * this file in accordance with the terms of the MIT license,
 * a copy of which can be found in the LICENSE.txt file or at
 * http://opensource.org/licenses/MIT.
 */
package runtime.intrinsic.tran;

import runtime.rep.Tuple;
import runtime.intrinsic.IntrinsicLambda;
import runtime.rep.Lambda;
import runtime.tran.TransactionManager;

/**
 * Takes a tuple of input boxes, a function and a tuple of output boxes.
 * Within a transaction, runs the function on the input boxes and writes
 * the result to the output boxes. If the function doesn't attempt any box
 * operations requiring relationships outside those already established for
 * reading arguments and writing the result, then it is guaranteed to run
 * without retrying.
 *
 * @author Basil Hosmer
 */
public final class _transfers extends IntrinsicLambda
{
    public static final _transfers INSTANCE = new _transfers(); 
    public static final String NAME = "transfers";

    public String getName()
    {
        return NAME;
    }

    public Object apply(final Object arg)
    {
        final Tuple args = (Tuple)arg;
        return invoke((Tuple)args.get(0), (Lambda)args.get(1), (Tuple)args.get(2));
    }

    public static Tuple invoke(
        final Tuple writes, final Lambda f, final Tuple reads)
    {
        TransactionManager.transfers(writes, f, reads);
        return Tuple.UNIT;
    }
}
