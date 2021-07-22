package org.function;

import java.util.function.Function;

/**
 * A collection of ‘particular functions of a very general nature’.
 * <p>
 * References:<br>
 * <a id="1">[1] Curry, Feys, and Craig. <i>Combinatory Logic,</i> Vol. I.</a><br>
 * <a id="2">[2] Rosenbloom. <i>The Elements of Mathematical Logic.</i></a><br>
 * <a id="3">[3] Schönfinkel. ‘On the building blocks of mathematical logic’,
 * trans. by Bauer-Mengelberg, in Van Heijenoort's <i>From Frege to Gödel.</i></a>
 */
public class Combinators
{
	private Combinators() { /* No instantiation. */ }

	/**
	 * Returns a functional interface that takes a value {@code A1} and
	 * returns the {@code A1} value.
	 * <p>
	 * This is the identity function (<i>die Identitätsfunktion</i>
	 * {@code I}, <a href="#3">[3]</a>; the <i>elementary identificator,</i>
	 * <a href="#1">[1]</a>; <a href="#2">[2]</a>).
	 * <p>
	 * Ix = x
	 * = Cxx
	 * = (Cx)(Cx)
	 * = SCCx,<br>
	 * therefore I = SCC<br>
	 * (See <a href="#3">[3]</a>.)
	 *
	 * @param <A1> a type
	 * @return a curried function
	 * @see java.util.function.Function#identity()
	 */
	public static <A1> Function<A1, A1> i()		{ return x -> x; }

	/**
	 * Returns a functional interface that takes a value {@code A1} and
	 * returns a functional interface that takes a value {@code A2} and
	 * returns the {@code A1} value.
	 * <p>
	 * This is the constancy function (<i>die Konstanzfunktion</i>
	 * {@code C}, <a href="#3">[3]</a>; the <i>elementary cancellator,</i>
	 * {@code K}, <a href="#1">[1]</a>; {@code K}, <a href="#2">[2]</a>).
	 * <p>
	 * Cxy = x<br>
	 * (See <a href="#3">[3]</a>.)
	 *
	 * @param <A1> a type
	 * @param <A2> a type
	 * @return a curried function
	 */
	public static <A1, A2> Function<A1, Function<A2, A1>> c()
	{
		return x -> y -> x;
	}

	/**
	 * Returns a functional interface that takes a functional interface
	 * that takes a value {@code A1} and returns a functional interface
	 * that takes a value {@code A2} and returns a value {@code A3}, and
	 * returns a functional interface that takes a value {@code A2} and
	 * returns a functional interface that takes a value {@code A1} and
	 * applies the to-left function to this value, obtaining another
	 * function to apply to the {@code A2} value, and returns a value
	 * {@code A3}.
	 * <p>
	 * This is the interchange function (<i>die VerTauschungsfunktion</i>
	 * {@code T}, <a href="#3">[3]</a>; the <i>elementary permutator,</i>
	 * {@code C}, <a href="#1">[1]</a>; {@code C}, <a href="#2">[2]</a>).
	 * <p>
	 * Tfyx = fxy
	 * = (fx)(Сyx)
	 * = (Sf)(Сy)x
	 * = Z(Sf)Cyx
	 * = (ZZSf)Cyx
	 * = (ZZSf)(CCf)yx
	 * = S(ZZS)(CC)fyx,<br>
	 * therefore T = S(ZZS)(CC)
	 * = S((S(CS)C)(S(CS)C)S)(CC)<br>
	 * (See <a href="#3">[3]</a>.)
	 *
	 * @param <A1> a type
	 * @param <A2> a type
	 * @param <A3> a type
	 * @return a curried function
	 */
	public static <A1, A2, A3> Function<Function<A1, Function<A2, A3>>,
				Function<A2,
				Function<A1, A3>>> t()
	{
		return f -> y -> x -> f
			.apply(x)
			.apply(y);
	}

	/**
	 * Returns a functional interface that takes a functional interface
	 * that takes a value {@code A2} and returns a value {@code A3}, and
	 * returns a functional interface that takes a functional interface
	 * that takes a value {@code A1} and returns a value {@code A2}, and
	 * returns a functional interface that takes a value {@code A1} and
	 * applies the rightmost function to this value, obtaining a value
	 * {@code A2} to which the leftmost function is applied to, and
	 * returns a value {@code A3}.
	 * <p>
	 * This is the composition function (<i>die Zusammensetzungsfunktion</i>
	 * {@code Z}, <a href="#3">[3]</a>; the <i>elementary compositor,</i>
	 * {@code B}, whose name implies ‘suBstitution’, <a href="#1">[1]</a>;
	 * {@code B}, <a href="#2">[2]</a>).
	 * <p>
	 * Zfgx = f(gx)
	 * = (Cfx)(gx)
	 * = S(Cf)gx
	 * = (CSf)(Cf)gx
	 * = S(CS)Cfgx,<br>
	 * therefore Z = S(CS)C<br>
	 * (See <a href="#3">[3]</a>.)
	 *
	 * @param <A1> a type
	 * @param <A2> a type
	 * @param <A3> a type
	 * @return a curried function
	 * @see java.util.function.Function#compose(java.util.function.Function)
	 */
	public static <A1, A2, A3> Function<Function<A2, A3>,
				Function<Function<A1, A2>,
				Function<A1, A3>>> z()
	{
		return f -> g -> x -> f
			.apply(g
				.apply(x));
	}

	/**
	 * Returns a functional interface that takes a functional interface
	 * that takes a value {@code A1} and returns a functional interface
	 * that takes a value {@code A2} and returns a value {@code A3}, and
	 * returns a functional interface that takes a functional interface
	 * that takes a value {@code A1} and returns a value {@code A2}, and
	 * returns a functional interface that takes a value {@code A1} and
	 * applies the leftmost function to this value, obtaining another
	 * function to apply to the {@code A2} value obtained from applying
	 * the rightmost function to the {@code A1} value, and returns a value
	 * {@code A3}.
	 * <p>
	 * This is the fusion function (<i>die VerSchmelzungsfunktion</i>
	 * {@code S}, <a href="#3">[3]</a>; {@code S}, <a href="#1">[1]</a>;
	 * a <i>substitution</i> operator, {@code A}, <a href="#2">[2]</a>).
	 * <p>
	 * Sfgx = (fx)(gx)<br>
	 * (See <a href="#3">[3]</a>.)
	 *
	 * @param <A1> a type
	 * @param <A2> a type
	 * @param <A3> a type
	 * @return a curried function
	 */
	public static <A1, A2, A3> Function<Function<A1, Function<A2, A3>>,
				Function<Function<A1, A2>,
				Function<A1, A3>>> s()
	{
		return f -> g -> x -> f
			.apply(x)
			.apply(g
				.apply(x));
	}

	/**
	 * Returns a functional interface that takes a functional interface
	 * that takes a value {@code A1} and returns a functional interface
	 * that takes a value {@code A1} and returns a value {@code A2}, and
	 * returns a functional interface that takes a value {@code A1} and
	 * applies the to-left function to this value, obtaining another
	 * function to apply to the {@code A1} value, and returns a value
	 * {@code A2}.
	 * <p>
	 * This is the <i>elementary duplicator,</i> {@code W} (its name
	 * implies repetition, i.e. ‘uu’ or ‘vv’, <a href="#1">[1]</a>;
	 * <a href="#2">[2]</a>).
	 * <p>
	 * Wfx = fxx
	 * = [||]fx||Cx|fx
	 * = [||]fx|||SCfx
	 * = [|||]Sf||SCfx
	 * = [||||]SS|SCfx,<br>
	 * therefore W = [||]SS|SC<br>
	 * (See <a href="#2">[2]</a>.)
	 * <p>
	 * Wfx = fxx
	 * = Tfxx
	 * = TTxfx
	 * = T(TTx)xf
	 * = (TTx)(TTx)f
	 * = S(TT)(TT)xf
	 * = T(S(TT)(TT))fx,<br>
	 * therefore W = T(S(TT)(TT))
	 * = (S((S(CS)C)(S(CS)C)S)(CC))(S(
	 *(S((S(CS)C)(S(CS)C)S)(CC))(S((S(CS)C)(S(CS)C)S)(CC)))(
	 *(S((S(CS)C)(S(CS)C)S)(CC))(S((S(CS)C)(S(CS)C)S)(CC))))<br>
	 * (See <a href="#1">[1]</a>.)
	 *
	 * @param <A1> a type
	 * @param <A2> a type
	 * @return a curried function
	 */
	public static <A1, A2> Function<Function<A1, Function<A1, A2>>,
				Function<A1, A2>> w()
	{
		return f -> x -> f
			.apply(x)
			.apply(x);
	}
}
