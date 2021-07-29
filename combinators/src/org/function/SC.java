package org.function;

import java.util.function.Function;

/**
 * The {@code I}, {@code T}, {@code Z}, and {@code W} combinators defined in
 * terms of {@code C} and {@code S} exclusively; and also, defined in terms of
 * some combination of {@code C}, {@code T}, {@code Z}, and {@code S}.
 *
 * @see Combinators
 */
public class SC
{
	private SC() { /* No instantiation. */ }

	/* I D E N T I T Y. (C, S): 1 + 3 */

	/**
	 * The {@code Cxx} definition of the identity function.
	 *
	 * @param <A1> a type
	 * @return a curried function
	 */
	public static <A1> Function<A1, A1> cxx()
	{
		return x -> {
			// (C(x))
			final Function<A1, A1> f_ = SC
				.<A1, A1>c_()
				.apply(x);
			return f_.apply(x);
		};
	}	// (C(x)) (x)

	/**
	 * The {@code (Cx)(Cx)} definition of the identity function.
	 *
	 * @param <A1> a type
	 * @return a curried function
	 */
	public static <A1> Function<A1, A1> cxcx()
	{
		return x -> {
			// (C(x))
			final Function<Function<A1, A1>, A1> f_ = SC
				.<A1, Function<A1, A1>>c_()
				.apply(x);

			// (C(x))
			final Function<A1, A1> g_ = SC
				.<A1, A1>c_()
				.apply(x);
			return f_.apply(g_);
		};
	}	// (C(x)) (C(x))

	/**
	 * The {@code SCCx} definition of the identity function.
	 *
	 * @param <A1> a type
	 * @return a curried function
	 */
	static <A1> Function<A1, A1> sccx()
	{
		return x -> {
			// (S)
			final S<A1, Function<A1, A1>, A1> f_ = SC
				.<A1, /* B1 */
					Function<A1, A1>,	/* B2 */
					A1>s_();		/* B3 */

			// (C)
			final Function<A1,
					Function<Function<A1, A1>, A1>> g_ = SC
				.<A1, Function<A1, A1>>c_();

			// (S(C))
			final Function<Function<A1, Function<A1, A1>>,
						Function<A1, A1>> h_ = f_
				.apply(g_);

			// (C)
			final Function<A1, Function<A1, A1>> ff_ = SC
				.<A1, A1>c_();

			// ((S(C)) (C))
			final Function<A1, A1> gg_ = h_.apply(ff_);
			return gg_.apply(x);
		};
	}	// ((S(C)) (C)) (x)

	/**
	 * The {@code SCC} definition of the identity function.
	 *
	 * @param <A1> a type
	 * @return a curried function
	 */
	public static <A1> Function<A1, A1> scc()
	{
		return SC.<A1, Function<A1, A1>, A1>s_()
			.apply(SC.<A1, Function<A1, A1>>c_())
			.apply(SC.<A1, A1>c_());
	}	// (S(C)) (C)




	/* I N T E R C H A N G E. (C, S; Z): 2 + 6 */

	/**
	 * The {@code (fx)(Сyx)} definition of the interchange function.
	 *
	 * @param <A1> a type
	 * @param <A2> a type
	 * @param <A3> a type
	 * @return a curried function
	 */
	public static <A1, A2, A3> Function<Function<A1, Function<A2, A3>>,
				Function<A2,
				Function<A1, A3>>> fxcyx()
	{
		return f -> y -> x -> {
			// (f(x))
			final Function<A2, A3> f_ = f.apply(x);

			// (C(y))
			final Function<A1, A2> g_ = SC
				.<A2, A1>c_()
				.apply(y);
			return f_.apply(g_
					.apply(x));
		};
	}	// (f(x)) ((С(y)) (x))

	/**
	 * The {@code (Sf)(Сy)x} definition of the interchange function.
	 *
	 * @param <A1> a type
	 * @param <A2> a type
	 * @param <A3> a type
	 * @return a curried function
	 */
	public static <A1, A2, A3> Function<Function<A1, Function<A2, A3>>,
				Function<A2,
				Function<A1, A3>>> sfcyx()
	{
		return f -> y -> x -> {
			// (S(f))
			final Function<Function<A1, A2>,
						Function<A1, A3>> f_ = SC
				.<A1, A2, A3>s_()
				.apply(f);

			// (C(y))
			final Function<A1, A2> g_ = SC
				.<A2, A1>c_()
				.apply(y);

			// ((S(f)) (C(y)))
			final Function<A1, A3> h_ = f_.apply(g_);
			return h_.apply(x);
		};
	}	// ((S(f)) (С(y))) (x)

	/**
	 * The {@code Z(Sf)Cyx} definition of the interchange function.
	 *
	 * @param <A1> a type
	 * @param <A2> a type
	 * @param <A3> a type
	 * @return a curried function
	 */
	public static <A1, A2, A3> Function<Function<A1, Function<A2, A3>>,
				Function<A2,
				Function<A1, A3>>> zsfcyx()
	{
		return f -> y -> x -> {
			// (Z)
			final Z<A2, Function<A1, A2>, Function<A1, A3>> f_ = SC
				.<A2, /* B1 */
					Function<A1, A2>,	/* B2 */
					Function<A1, A3>>z_();	/* B3 */

			// (S(f))
			final Function<Function<A1, A2>,
						Function<A1, A3>> g_ = SC
				.<A1, A2, A3>s_()
				.apply(f);

			// (Z(S(f)))
			final Function<Function<A2, Function<A1, A2>>,
					Function<A2, Function<A1, A3>>> h_ = f_
				.apply(g_);

			// ((Z(S(f))) (C))
			final Function<A2, Function<A1, A3>> ff_ = h_
				.apply(SC.<A2, A1>c_());
			return ff_.apply(y)
				.apply(x);
		};
	}	// (((Z(S(f))) (C)) (y)) (x)

	private static <A1, A2, A3> Function<Function<A1, Function<A2, A3>>,
				Function<Function<A2, Function<A1, A2>>,
				Function<A2, Function<A1, A3>>>> zzs_t()
	{
		// (Z)
		final Z<Function<A1, Function<A2, A3>>,
				Function<Function<A1, A2>,
						Function<A1, A3>>,
				Function<Function<A2, Function<A1, A2>>,
					Function<A2, Function<A1, A3>>>> f_ = SC
			.<Function<A1, Function<A2, A3>>, /* B1 */
				Function<Function<A1, A2>,
						Function<A1, A3>>,	/* B2 */
				Function<Function<A2, Function<A1, A2>>, /* B3 */
					Function<A2, Function<A1, A3>>>>z_();

		// (Z)
		final Z<A2, Function<A1, A2>, Function<A1, A3>> g_ = SC
			.<A2, /* B1 */
				Function<A1, A2>,	/* B2 */
				Function<A1, A3>>z_();	/* B3 */

		// (Z(Z))
		final Function<Function<Function<A1, Function<A2, A3>>,
					Function<Function<A1, A2>,
						Function<A1, A3>>>,
				Function<Function<A1, Function<A2, A3>>,
					Function<Function<A2,
						Function<A1, A2>>,
					Function<A2, Function<A1, A3>>>>> h_ = f_
			.apply(g_);
		return h_.apply(SC.<A1, A2, A3>s_());
	}	// ((Z(Z)) (S))

	/**
	 * The {@code (ZZSf)Cyx} definition of the interchange function.
	 *
	 * @param <A1> a type
	 * @param <A2> a type
	 * @param <A3> a type
	 * @return a curried function
	 */
	public static <A1, A2, A3> Function<Function<A1, Function<A2, A3>>,
				Function<A2,
				Function<A1, A3>>> zzsfcyx()
	{
		return f -> y -> x -> {
			// ((Z(Z)) (S))
			final Function<Function<A1, Function<A2, A3>>,
					Function<Function<A2, Function<A1, A2>>,
					Function<A2, Function<A1, A3>>>> f_ =
								zzs_t();

			// (((Z(Z)) (S)) (f))
			final Function<Function<A2, Function<A1, A2>>,
					Function<A2, Function<A1, A3>>> g_ = f_
				.apply(f);

			// ((((Z(Z)) (S)) (f)) (C))
			final Function<A2, Function<A1, A3>> h_ = g_
				.apply(SC.<A2, A1>c_());
			return h_.apply(y)
				.apply(x);
		};
	}	// (((((Z(Z)) (S)) (f)) (C)) (y)) (x)

	/**
	 * The {@code (ZZSf)(CCf)yx} definition of the interchange function.
	 *
	 * @param <A1> a type
	 * @param <A2> a type
	 * @param <A3> a type
	 * @return a curried function
	 */
	public static <A1, A2, A3> Function<Function<A1, Function<A2, A3>>,
				Function<A2,
				Function<A1, A3>>> zzsfccfyx()
	{
		return f -> y -> x -> {
			// ((Z(Z)) (S))
			final Function<Function<A1, Function<A2, A3>>,
					Function<Function<A2, Function<A1, A2>>,
					Function<A2, Function<A1, A3>>>> f_ =
								zzs_t();

			// (((Z(Z)) (S)) (f))
			final Function<Function<A2, Function<A1, A2>>,
					Function<A2, Function<A1, A3>>> g_ = f_
				.apply(f);

			// (C)
			final C<Function<A2, Function<A1, A2>>,
					Function<A1, Function<A2, A3>>> h_ = SC
				.<Function<A2, Function<A1, A2>>,
					Function<A1, Function<A2, A3>>>c_();

			// (C(C))
			final Function<Function<A1, Function<A2, A3>>,
					Function<A2, Function<A1, A2>>> ff_ = h_
				.apply(SC.<A2, A1>c_());

			// ((C(C)) (f))
			final Function<A2, Function<A1, A2>> gg_ = ff_.apply(f);

			// ((((Z(Z)) (S)) (f)) ((C(C)) (f)))
			final Function<A2, Function<A1, A3>> hh_ = g_.apply(gg_);
			return hh_.apply(y)
				.apply(x);
		};
	}	// (((((Z(Z)) (S)) (f)) ((C(C)) (f))) (y)) (x)

	/**
	 * The {@code S(ZZS)(CC)fyx} definition of the interchange function.
	 *
	 * @param <A1> a type
	 * @param <A2> a type
	 * @param <A3> a type
	 * @return a curried function
	 */
	static <A1, A2, A3> Function<Function<A1, Function<A2, A3>>,
				Function<A2,
				Function<A1, A3>>> szzsccfyx()
	{
		return f -> y -> x -> {
			// (S)
			final S<Function<A1, Function<A2, A3>>,
					Function<A2, Function<A1, A2>>,
					Function<A2, Function<A1, A3>>> f_ = SC
				.<Function<A1, Function<A2, A3>>, /* B1 */
					Function<A2, Function<A1, A2>>,	/* B2 */
					Function<A2, Function<A1, A3>>>s_(); /* B3 */

			// ((Z(Z)) (S))
			final Function<Function<A1, Function<A2, A3>>,
					Function<Function<A2, Function<A1, A2>>,
					Function<A2, Function<A1, A3>>>> g_ =
								zzs_t();

			// (S(((Z(Z)) (S))))
			final Function<Function<Function<A1, Function<A2, A3>>,
						Function<A2, Function<A1, A2>>>,
					Function<Function<A1, Function<A2, A3>>,
						Function<A2, Function<A1, A3>>>> h_ =
									f_
				.apply(g_);

			// (C)
			final C<Function<A2, Function<A1, A2>>,
					Function<A1, Function<A2, A3>>> ff_ = SC
				.<Function<A2, Function<A1, A2>>,
					Function<A1, Function<A2, A3>>>c_();

			// (C(C))
			final Function<Function<A1, Function<A2, A3>>,
					Function<A2, Function<A1, A2>>> gg_ = ff_
				.apply(SC.<A2, A1>c_());

			// ((S(((Z(Z)) (S)))) (C(C)))
			final Function<Function<A1, Function<A2, A3>>,
					Function<A2, Function<A1, A3>>> hh_ = h_
				.apply(gg_);

			// (((S(((Z(Z)) (S)))) (C(C))) (f))
			final Function<A2, Function<A1, A3>> fff_ = hh_.apply(f);
			return fff_.apply(y)
				.apply(x);
		};
	}	// ((((S(((Z(Z)) (S)))) (C(C))) (f)) (y)) (x)

	/**
	 * The {@code S(ZZS)(CC)} definition of the interchange function.
	 *
	 * @param <A1> a type
	 * @param <A2> a type
	 * @param <A3> a type
	 * @return a curried function
	 */
	public static <A1, A2, A3> Function<Function<A1, Function<A2, A3>>,
				Function<A2,
				Function<A1, A3>>> szzscc()
	{
		return SC.<Function<A1, Function<A2, A3>>, /* B1 */
					Function<A2, Function<A1, A2>>, /* B2 */
					Function<A2, Function<A1, A3>>>s_() /* B3 */
			.apply(zzs_t())
			.apply(SC.<Function<A2, Function<A1, A2>>,
					Function<A1, Function<A2, A3>>>c_()
				.apply(SC.<A2, A1>c_()));
	}	// (S(((Z(Z)) (S)))) (C(C))

	/**
	 * The {@code S((S(CS)C)(S(CS)C)S)(CC)} definition of the interchange
	 * function.
	 *
	 * @param <A1> a type
	 * @param <A2> a type
	 * @param <A3> a type
	 * @return a curried function
	 */
	public static <A1, A2, A3> Function<Function<A1, Function<A2, A3>>,
				Function<A2,
				Function<A1, A3>>> sscscscscscc()
	{
		return SC.<Function<A1, Function<A2, A3>>, /* B1 */
					Function<A2, Function<A1, A2>>, /* B2 */
					Function<A2, Function<A1, A3>>>s_() /* B3 */
			.apply(SC.<Function<A1, Function<A2, A3>>, /* B1 */
					Function<Function<A1, A2>,
						Function<A1, A3>>, /* B2 */
					Function<Function<A2, Function<A1, A2>>,
						Function<A2, Function<A1, A3>>>> /* B3 */
									scsc()
				.apply(SC.<A2, /* B1 */
						Function<A1, A2>, /* B2 */
						Function<A1, A3>>scsc()) /* B3 */
				.apply(SC.<A1, A2, A3>s_()))
			.apply(SC.<Function<A2, Function<A1, A2>>,
					Function<A1, Function<A2, A3>>>c_()
				.apply(SC.<A2, A1>c_()));
	}	// (S(((((S(C(S))) (C)) ((S(C(S))) (C))) (S)))) (C(C))




	/* C O M P O S I T I O N. (C, S): 1 + 4 */

	/**
	 * The {@code (Cfx)(gx)} definition of the composition function.
	 *
	 * @param <A1> a type
	 * @param <A2> a type
	 * @param <A3> a type
	 * @return a curried function
	 */
	public static <A1, A2, A3> Function<Function<A2, A3>,
				Function<Function<A1, A2>,
				Function<A1, A3>>> cfxgx()
	{
		return f -> g -> x -> {
			// (g(x))
			final A2 a2 = g.apply(x);

			// (C(f))
			final Function<A1, Function<A2, A3>> f_ = SC
				.<Function<A2, A3>, A1>c_()
				.apply(f);

			// ((C(f)) (x))
			final Function<A2, A3> g_ = f_.apply(x);
			return g_.apply(a2);
		};
	}	// ((C(f)) (x)) (g(x))

	/**
	 * The {@code S(Cf)gx} definition of the composition function.
	 *
	 * @param <A1> a type
	 * @param <A2> a type
	 * @param <A3> a type
	 * @return a curried function
	 */
	public static <A1, A2, A3> Function<Function<A2, A3>,
				Function<Function<A1, A2>,
				Function<A1, A3>>> scfgx()
	{
		return f -> g -> x -> {
			// (S)
			final S<A1, A2, A3> f_ = SC.<A1, A2, A3>s_();

			// (C(f))
			final Function<A1, Function<A2, A3>> g_ = SC
				.<Function<A2, A3>, A1>c_()
				.apply(f);

			// (S(C(f)))
			final Function<Function<A1, A2>,
						Function<A1, A3>> h_ = f_
				.apply(g_);
			return h_.apply(g)
				.apply(x);
		};
	}	// ((S(C(f))) (g)) (x)

	/**
	 * The {@code (CSf)(Cf)gx} definition of the composition function.
	 *
	 * @param <A1> a type
	 * @param <A2> a type
	 * @param <A3> a type
	 * @return a curried function
	 */
	public static <A1, A2, A3> Function<Function<A2, A3>,
				Function<Function<A1, A2>,
				Function<A1, A3>>> csfcfgx()
	{
		return f -> g -> x -> {
			// (C(S))
			final Function<Function<A2, A3>, S<A1, A2, A3>> f_ = SC
				.<S<A1, A2, A3>, Function<A2, A3>>c_()
				.apply(SC.<A1, A2, A3>s_());

			// ((C(S)) (f))
			final S<A1, A2, A3> g_ = f_.apply(f);

			// (C(f))
			final Function<A1, Function<A2, A3>> h_ = SC
				.<Function<A2, A3>, A1>c_()
				.apply(f);

			// (((C(S)) (f)) (C(f)))
			final Function<Function<A1, A2>,
						Function<A1, A3>> ff_ = g_
				.apply(h_);
			return ff_.apply(g)
				.apply(x);
		};
	}	// ((((C(S)) (f)) (C(f))) (g)) (x)

	/**
	 * The {@code S(CS)Cfgx} definition of the composition function.
	 *
	 * @param <A1> a type
	 * @param <A2> a type
	 * @param <A3> a type
	 * @return a curried function
	 */
	static <A1, A2, A3> Function<Function<A2, A3>,
				Function<Function<A1, A2>,
				Function<A1, A3>>> scscfgx()
	{
		return f -> g -> x -> {
			// (S)
			final S<Function<A2, A3>,
					Function<A1, Function<A2, A3>>,
					Function<Function<A1, A2>,
						Function<A1, A3>>> f_ = SC
				.<Function<A2, A3>, /* B1 */
					Function<A1, Function<A2, A3>>,	/* B2 */
					Function<Function<A1, A2>,
						Function<A1, A3>>>s_();	/* B3 */

			// Note that Function<Function<A2, A3>, S<A1, A2, A3>>
			// is not used as the type of g_ in order to satisfy
			// the application of f_ to it, i.e. f_.apply(g_).

			// (C(S))
			final Function<Function<A2, A3>,
					Function<Function<A1, Function<A2, A3>>,
						Function<Function<A1, A2>,
						Function<A1, A3>>>> g_ = SC
				.<Function<Function<A1, Function<A2, A3>>,
							Function<Function<A1, A2>,
							Function<A1, A3>>>,
						Function<A2, A3>>c_()
				.apply(SC.<A1, A2, A3>s_());

			// (S (C(S)))
			final Function<Function<Function<A2, A3>,
						Function<A1, Function<A2, A3>>>,
					Function<Function<A2, A3>,
						Function<Function<A1, A2>,
							Function<A1, A3>>>> h_ =
									f_
				.apply(g_);

			// (C)
			final C<Function<A2, A3>, A1> ff_ = SC
				.<Function<A2, A3>, A1>c_();

			// ((S (C(S))) (C))
			final Function<Function<A2, A3>,
					Function<Function<A1, A2>,
						Function<A1, A3>>> gg_ = h_
				.apply(ff_);
			return gg_.apply(f)
				.apply(g)
				.apply(x);
		};
	}	// ((((S(C(S))) (C)) (f)) (g)) (x)

	/**
	 * The {@code S(CS)C} definition of the composition function.
	 *
	 * @param <A1> a type
	 * @param <A2> a type
	 * @param <A3> a type
	 * @return a curried function
	 */
	public static <A1, A2, A3> Function<Function<A2, A3>,
				Function<Function<A1, A2>,
				Function<A1, A3>>> scsc()
	{
		return SC.<Function<A2, A3>,
					Function<A1, Function<A2, A3>>,
					Function<Function<A1, A2>,
						Function<A1, A3>>>s_()
						/* See the note in scscfgx. */
			.apply(SC.<Function<Function<A1, Function<A2, A3>>,
						Function<Function<A1, A2>,
						Function<A1, A3>>>,
					Function<A2, A3>>c_()
				.apply(SC.<A1, A2, A3>s_()))
			.apply(SC.<Function<A2, A3>, A1>c_());
	}	// (S(C(S))) (C)




	/* D U P L I C A T O R. (C, S; T): 3 + 10 */

	/**
	 * The {@code fx(Cx(fx))} definition of the elementary duplicator.
	 *
	 * @param <A1> a type
	 * @param <A2> a type
	 * @return a curried function
	 */
	public static <A1, A2> Function<Function<A1, Function<A1, A2>>,
				Function<A1, A2>> fxcxfx()
	{
		return f -> x -> {
			// (f(x))
			final Function<A1, A2> f_ = f.apply(x);

			// (C(x))
			final Function<Function<A1, A2>, A1> g_ = SC
				.<A1, Function<A1, A2>>c_()
				.apply(x);

			// ((C(x)) (f(x)))
			final A1 a1 = g_.apply(f_);
			return f_.apply(a1);
		};
	}	// (f(x)) ((C(x)) (f(x)))

	/**
	 * The {@code fx(SCfx)} definition of the elementary duplicator.
	 *
	 * @param <A1> a type
	 * @param <A2> a type
	 * @return a curried function
	 */
	public static <A1, A2> Function<Function<A1, Function<A1, A2>>,
				Function<A1, A2>> fxscfx()
	{
		return f -> x -> {
			// (f(x))
			final Function<A1, A2> f_ = f.apply(x);

			// (S)
			final S<A1, Function<A1, A2>, A1> g_ = SC
				.<A1, /* B1 */
					Function<A1, A2>,	/* B2 */
					A1>s_();		/* B3 */

			// (C)
			final C<A1, Function<A1, A2>> h_ = SC
				.<A1, Function<A1, A2>>c_();

			// (S(C))
			final Function<Function<A1, Function<A1, A2>>,
						Function<A1, A1>> ff_ = g_
				.apply(h_);

			// (((S(C)) (f)) (x))
			final A1 a1 = ff_
				.apply(f)
				.apply(x);
			return f_.apply(a1);
		};
	}	// (f(x)) (((S(C)) (f)) (x))

	/**
	 * The {@code Sf(SCf)x} definition of the elementary duplicator.
	 *
	 * @param <A1> a type
	 * @param <A2> a type
	 * @return a curried function
	 */
	public static <A1, A2> Function<Function<A1, Function<A1, A2>>,
				Function<A1, A2>> sfscfx()
	{
		return f -> x -> {
			// (S)
			final S<A1, A1, A2> f_ = SC.<A1, A1, A2>s_();

			// (S(f))
			final Function<Function<A1, A1>,
						Function<A1, A2>> g_ = f_
				.apply(f);

			// (S)
			final S<A1, Function<A1, A2>, A1> h_ = SC
				.<A1, /* B1 */
					Function<A1, A2>,	/* B2 */
					A1>s_();		/* B3 */

			// (S(C))
			final Function<Function<A1, Function<A1, A2>>,
					Function<A1, A1>> ff_ = h_
				.apply(SC.<A1, Function<A1, A2>>c_());

			// ((S(C)) (f))
			final Function<A1, A1> gg_ = ff_.apply(f);

			// (S(f) ((S(C)) (f)))
			final Function<A1, A2> hh_ = g_.apply(gg_);
			return hh_.apply(x);
		};
	}	// (S(f) ((S(C)) (f))) (x)

	/**
	 * The {@code SS(SC)fx} definition of the elementary duplicator.
	 *
	 * @param <A1> a type
	 * @param <A2> a type
	 * @return a curried function
	 */
	static <A1, A2> Function<Function<A1, Function<A1, A2>>,
				Function<A1, A2>> ssscfx()
	{
		return f -> x -> {
			// (S)
			final S<Function<A1, Function<A1, A2>>,
					Function<A1, A1>,
					Function<A1, A2>> f_ = SC
				.<Function<A1, Function<A1, A2>>, /* B1 */
					Function<A1, A1>,	/* B2 */
					Function<A1, A2>>s_();	/* B3 */

			// (S(S))
			final Function<Function<Function<A1, Function<A1, A2>>,
							Function<A1, A1>>,
					Function<Function<A1, Function<A1, A2>>,
						Function<A1, A2>>> g_ = f_
				.apply(SC.<A1, A1, A2>s_());

			// (S)
			final S<A1, Function<A1, A2>, A1> h_ = SC
				.<A1, /* B1 */
					Function<A1, A2>,	/* B2 */
					A1>s_();		/* B3 */

			// (S(C))
			final Function<Function<A1, Function<A1, A2>>,
						Function<A1, A1>> ff_ = h_
				.apply(SC.<A1, Function<A1, A2>>c_());

			// ((S(S)) (S(C)))
			final Function<Function<A1, Function<A1, A2>>,
						Function<A1, A2>> gg_ = g_
				.apply(ff_);
			return gg_.apply(f)
				.apply(x);
		};
	}	// (((S(S)) (S(C))) (f)) (x)

	/**
	 * The {@code SS(SC)} definition of the elementary duplicator.
	 *
	 * @param <A1> a type
	 * @param <A2> a type
	 * @return a curried function
	 */
	public static <A1, A2> Function<Function<A1, Function<A1, A2>>,
				Function<A1, A2>> sssc()
	{
		return SC.<Function<A1, Function<A1, A2>>, /* B1 */
				Function<A1, A1>,	/* B2 */
				Function<A1, A2>>s_()	/* B3 */
			.apply(SC.<A1, A1, A2>s_())
			.apply(SC.<A1, Function<A1, A2>, A1>s_()
				.apply(SC.<A1, Function<A1, A2>>c_()));
	}	// (S(S)) (S(C))

	/**
	 * The {@code Tfxx} definition of the elementary duplicator.
	 *
	 * @param <A1> a type
	 * @param <A2> a type
	 * @return a curried function
	 */
	public static <A1, A2> Function<Function<A1, Function<A1, A2>>,
				Function<A1, A2>> tfxx()
	{
		return f -> x -> SC
			.<A1, A1, A2>t_()
			.apply(f)
			.apply(x)
			.apply(x);
	}	// ((T(f)) (x)) (x)

	private static <A1, A2> Function<A1,
				Function<Function<A1, Function<A1, A2>>,
				Function<A1, A2>>> tt_w()
	{
		return SC.<Function<A1, Function<A1, A2>>, /* B1 */
				A1,			/* B2 */
				Function<A1, A2>>t_()	/* B3 */
			.apply(SC.<A1, A1, A2>t_());
	}	// (T(T))

	/**
	 * The {@code TTxfx} definition of the elementary duplicator.
	 *
	 * @param <A1> a type
	 * @param <A2> a type
	 * @return a curried function
	 */
	public static <A1, A2> Function<Function<A1, Function<A1, A2>>,
				Function<A1, A2>> ttxfx()
	{
		return f -> x -> SC
			.<A1, A2>tt_w()
			.apply(x)
			.apply(f)
			.apply(x);
	}	// (((T(T)) (x)) (f)) (x)

	/**
	 * The {@code T(TTx)xf} definition of the elementary duplicator.
	 *
	 * @param <A1> a type
	 * @param <A2> a type
	 * @return a curried function
	 */
	public static <A1, A2> Function<Function<A1, Function<A1, A2>>,
				Function<A1, A2>> tttxxf()
	{
		return f -> x -> {
			// (T)
			final T<Function<A1, Function<A1, A2>>, A1, A2> f_ = SC
				.<Function<A1, Function<A1, A2>>, /* B1 */
					A1,		/* B2 */
					A2>t_();	/* B3 */

			// ((T(T)) (x))
			final Function<Function<A1, Function<A1, A2>>,
						Function<A1, A2>> g_ = SC
				.<A1, A2>tt_w()
				.apply(x);

			// (T(((T(T)) (x))))
			final Function<A1,
					Function<Function<A1,
						Function<A1, A2>>, A2>> h_ = f_
				.apply(g_);
			return h_.apply(x)
				.apply(f);
		};
	}	// ((T(((T(T)) (x)))) (x)) (f)

	/**
	 * The {@code (TTx)(TTx)f} definition of the elementary duplicator.
	 *
	 * @param <A1> a type
	 * @param <A2> a type
	 * @return a curried function
	 */
	public static <A1, A2> Function<Function<A1, Function<A1, A2>>,
				Function<A1, A2>> ttxttxf()
	{
		return f -> x -> {
			// (T)
			final T<Function<Function<A1, Function<A1, A2>>,
							Function<A1, A2>>,
					A1,
					Function<Function<A1, Function<A1, A2>>,
									A2>>
								f_ = SC
				.<Function<Function<A1, Function<A1, A2>>,
							Function<A1, A2>>, /* B1 */
					A1,			/* B2 */
					Function<Function<A1, Function<A1, A2>>,
						A2>>t_();	/* B3 */

			// (T)
			final T<Function<A1, Function<A1, A2>>, A1, A2> g_ = SC
				.<Function<A1, Function<A1, A2>>, /* B1 */
					A1,		/* B2 */
					A2>t_();	/* B3 */

			// ((T(T)) (x))
			final Function<Function<Function<A1, Function<A1, A2>>,
							Function<A1, A2>>,
					Function<Function<A1, Function<A1, A2>>,
									A2>>
								h_ = f_
				.apply(g_)
				.apply(x);

			// (T)
			final T<Function<A1, Function<A1, A2>>,
					A1,
					Function<A1, A2>> ff_ = SC
				.<Function<A1, Function<A1, A2>>, /* B1 */
					A1,			/* B2 */
					Function<A1, A2>>t_();	/* B3 */

			// (T)
			final T<A1, A1, A2> gg_ = SC.<A1, A1, A2>t_();

			// ((T(T)) (x))
			final Function<Function<A1, Function<A1, A2>>,
						Function<A1, A2>> hh_ = ff_
				.apply(gg_)
				.apply(x);

			// (((T(T)) (x)) ((T(T)) (x)))
			final Function<Function<A1, Function<A1, A2>>,
								A2> fff_ = h_
				.apply(hh_);
			return fff_.apply(f);
		};
	}	// (((T(T)) (x)) ((T(T)) (x))) (f)

	private static <A1, A2> Function<A1,
				Function<Function<A1, Function<A1, A2>>, A2>>
								stttt_w()
	{
		// (S)
		final S<A1, Function<Function<A1, Function<A1, A2>>,
							Function<A1, A2>>,
				Function<Function<A1,
						Function<A1, A2>>, A2>> f_ = SC
			.<A1, /* B1 */
				Function<Function<A1, Function<A1, A2>>,
							Function<A1, A2>>, /* B2 */
				Function<Function<A1, Function<A1, A2>>,
							A2>>s_();	/* B3 */

		// (T)
		final T<Function<Function<A1, Function<A1, A2>>,
							Function<A1, A2>>,
				A1,
				Function<Function<A1, Function<A1, A2>>,
								A2>> g_ = SC
			.<Function<Function<A1, Function<A1, A2>>,
						Function<A1, A2>>, /* B1 */
				A1,				/* B2 */
				Function<Function<A1, Function<A1, A2>>,
							A2>>t_(); /* B3 */

		// (T)
		final T<Function<A1, Function<A1, A2>>, A1, A2> h_ = SC
			.<Function<A1, Function<A1, A2>>, /* B1 */
				A1,			/* B2 */
				A2>t_();		/* B3 */

		// (S((T(T))))
		final Function<Function<A1,
					Function<Function<A1, Function<A1, A2>>,
							Function<A1, A2>>>,
				Function<A1,
					Function<Function<A1, Function<A1, A2>>,
								A2>>> ff_ = f_
			.apply(g_
				.apply(h_));

		// (T)
		final T<Function<A1, Function<A1, A2>>,
				A1,
				Function<A1, A2>> gg_ = SC
			.<Function<A1, Function<A1, A2>>, /* B1 */
				A1,			/* B2 */
				Function<A1, A2>>t_();	/* B3 */

		// (T(T))
		final Function<A1,
				Function<Function<A1, Function<A1, A2>>,
						Function<A1, A2>>> hh_ = gg_
			.apply(SC.<A1, A1, A2>t_());
		return ff_.apply(hh_);
	}	// ((S((T(T)))) (T(T)))

	/**
	 * The {@code S(TT)(TT)xf} definition of the elementary duplicator.
	 *
	 * @param <A1> a type
	 * @param <A2> a type
	 * @return a curried function
	 */
	public static <A1, A2> Function<Function<A1, Function<A1, A2>>,
				Function<A1, A2>> sttttxf()
	{
		return f -> x -> SC
			.<A1, A2>stttt_w()
			.apply(x)
			.apply(f);
	}	// (((S(T(T))) (T(T))) (x)) (f)

	/**
	 * The {@code T(S(TT)(TT))fx} definition of the elementary duplicator.
	 *
	 * @param <A1> a type
	 * @param <A2> a type
	 * @return a curried function
	 */
	static <A1, A2> Function<Function<A1, Function<A1, A2>>,
				Function<A1, A2>> tsttttfx()
	{
		return f -> x -> {
			// (T)
			final T<A1, Function<A1, Function<A1, A2>>, A2> f_ = SC
				.<A1, /* B1 */
					Function<A1, Function<A1, A2>>,	/* B2 */
					A2>t_();			/* B3 */

			// ((S(T(T))) (T(T)))
			final Function<A1,
					Function<Function<A1, Function<A1, A2>>,
								A2>> g_ = SC
				.<A1, A2>stttt_w();

			// (T(((S(T(T))) (T(T)))))
			final Function<Function<A1, Function<A1, A2>>,
						Function<A1, A2>> h_ = f_
				.apply(g_);
			return h_.apply(f)
				.apply(x);
		};
	}	// ((T(((S(T(T))) (T(T))))) (f)) (x)

	/**
	 * The {@code T(S(TT)(TT))} definition of the elementary duplicator.
	 *
	 * @param <A1> a type
	 * @param <A2> a type
	 * @return a curried function
	 */
	public static <A1, A2> Function<Function<A1, Function<A1, A2>>,
				Function<A1, A2>> tstttt()
	{
		return SC.<A1, /* B1 */
				Function<A1, Function<A1, A2>>,	/* B2 */
				A2>t_()				/* B3 */
			.apply(SC.<A1, A2>stttt_w());
	}	// (T(((S(T(T))) (T(T)))))

	/**
	 * The {@code (SSCSCSCSCSCC)(S((SSCSCSCSCSCC)(SSCSCSCSCSCC)((SSCSCSCSCSCC)(SSCSCSCSCSCC))))}
	 * definition of the elementary duplicator.
	 *
	 * @param <A1> a type
	 * @param <A2> a type
	 * @return a curried function
	 */
	public static <A1, A2> Function<Function<A1, Function<A1, A2>>,
							Function<A1, A2>>
		sscscscscscc_s_sscscscscscc_sscscscscscc_sscscscscscc_sscscscscscc()
	{
		return SC.<A1, /* B1 */
				Function<A1, Function<A1, A2>>,	/* B2 */
				A2>sscscscscscc()		/* B3 */
			.apply(SC.<A1, /* B1 */
					Function<Function<A1, Function<A1, A2>>,
						Function<A1, A2>>, /* B2 */
					Function<Function<A1, Function<A1, A2>>,
						A2>>s_()	/* B3 */
				.apply(SC.<Function<Function<A1, Function<A1, A2>>,
							Function<A1, A2>>, /* B1 */
						A1,		/* B2 */
						Function<Function<A1, Function<A1, A2>>,
							A2>>sscscscscscc() /* B3 */
					.apply(SC.<Function<A1, Function<A1, A2>>, /* B1 */
							A1,	/* B2 */
							A2>sscscscscscc())) /* B3 */
				.apply(SC.<Function<A1, Function<A1, A2>>, /* B1 */
						A1,			/* B2 */
						Function<A1, A2>>	/* B3 */
							sscscscscscc()
					.apply(SC.<A1, A1, A2>sscscscscscc())));
	}




	/* C O M B I N A T O R    T Y P E S    A N D    F U N C T I O N S. */

	/**
	 * The constancy function.
	 *
	 * @param <A1> a type
	 * @param <A2> a type
	 * @return a curried function
	 * @see Combinators#c()
	 */
	static <A1, A2> C<A1, A2> c_()		{ return x -> y -> x; }

	/**
	 * The interchange function.
	 *
	 * @param <A1> a type
	 * @param <A2> a type
	 * @param <A3> a type
	 * @return a curried function
	 * @see Combinators#t()
	 */
	static <A1, A2, A3> T<A1, A2, A3> t_()
	{
		return f -> y -> x -> f
			.apply(x)
			.apply(y);
	}

	/**
	 * The composition function.
	 *
	 * @param <A1> a type
	 * @param <A2> a type
	 * @param <A3> a type
	 * @return a curried function
	 * @see Combinators#z()
	 */
	static <A1, A2, A3> Z<A1, A2, A3> z_()
	{
		return f -> g -> x -> f
			.apply(g
				.apply(x));
	}

	/**
	 * The fusion function.
	 *
	 * @param <A1> a type
	 * @param <A2> a type
	 * @param <A3> a type
	 * @return a curried function
	 * @see Combinators#s()
	 */
	static <A1, A2, A3> S<A1, A2, A3> s_()
	{
		return f -> g -> x -> f
			.apply(x)
			.apply(g
				.apply(x));
	}




	/**
	 * The constancy {@link java.util.function.Function Function}.
	 *
	 * @param <A1> a type
	 * @param <A2> a type
	 * @see Combinators#c()
	 */
	interface C<A1, A2> extends Function<A1, Function<A2, A1>> { }

	/**
	 * The interchange {@link java.util.function.Function Function}.
	 *
	 * @param <A1> a type
	 * @param <A2> a type
	 * @param <A3> a type
	 * @see Combinators#t()
	 */
	interface T<A1, A2, A3> extends Function<Function<A1, Function<A2, A3>>,
					Function<A2,
					Function<A1, A3>>> { }

	/**
	 * The composition {@link java.util.function.Function Function}.
	 *
	 * @param <A1> a type
	 * @param <A2> a type
	 * @param <A3> a type
	 * @see Combinators#z()
	 */
	interface Z<A1, A2, A3> extends Function<Function<A2, A3>,
					Function<Function<A1, A2>,
					Function<A1, A3>>> { }

	/**
	 * The fusion {@link java.util.function.Function Function}.
	 *
	 * @param <A1> a type
	 * @param <A2> a type
	 * @param <A3> a type
	 * @see Combinators#s()
	 */
	interface S<A1, A2, A3> extends Function<Function<A1, Function<A2, A3>>,
					Function<Function<A1, A2>,
					Function<A1, A3>>> { }
}
