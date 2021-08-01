package org.function;

import java.util.Locale;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

class SCTests
{
	static {
		boolean assertable = false;

////		try {
////			// See jdk.compiler/com.sun.tools.javac.comp.Lower
////			assertable = !(SCTests.class
////				.getDeclaredField("$assertionsDisabled")
////				.getBoolean(null));
////		} catch (final ReflectiveOperationException e) {
////			throw new ExceptionInInitializerError(e);
////		}

		assert assertable = true;

		if (!assertable)
			throw new AssertionError();
	}

	private SCTests() { /* No instantiation. */ }

	private static <T> BiConsumer<T, T> uoeBCThrower()
	{
		return (left, right) -> {
			throw new UnsupportedOperationException();
		};
	}

	private static <T> BinaryOperator<T> uoeBOThrower()
	{
		return (left, right) -> {
			throw new UnsupportedOperationException();
		};
	}

	/* IDENTITY: 1 + 4 */
	static void testI(boolean inConcurrence)
	{
		assert Set.of(Combinators.<Void>i(),
				SC.<Void>cxx(),
				SC.<Void>cxcx(),
				SC.<Void>sccx(),
				SC.<Void>scc())
			.stream()
			.map(Combinators.<Function<Void,
					Function<Function<Void, Void>, Void>>>i()
				.apply(x -> i -> i
					.apply(x))	// Smullyan's _thrush_.
				.apply(null))
			.reduce(true,
				(left, right) -> left && right == null,
				uoeBOThrower());
	}

	private static <A1, A2> Function<Function<Function<A1, Function<A2, A1>>,
				Function<A2,
				Function<A1, A1>>>, A1> ttMapper(A1 a1, A2 a2)
	{
		return Combinators.<Function<Function<A1, Function<A2, A1>>,
					Function<A2,
					Function<A1,
					Function<Function<Function<A1,
							Function<A2, A1>>,
						Function<A2,
						Function<A1, A1>>>,
								A1>>>>>i()
			.apply(f -> y -> x -> t -> t
				.apply(f)
				.apply(y)
				.apply(x))
			.apply(Combinators.<A1, A2>c())
			.apply(a2)
			.apply(a1);
	}

	private static <A1, A2, A3> Set<Function<Function<A1, Function<A2, A3>>,
				Function<A2,
				Function<A1, A3>>>> tt()
	{
		return Set.of(Combinators.<A1, A2, A3>t(),
				SC.<A1, A2, A3>fxcyx(),
				SC.<A1, A2, A3>sfcyx(),
				SC.<A1, A2, A3>zsfcyx(),
				SC.<A1, A2, A3>zzsfcyx(),
				SC.<A1, A2, A3>zzsfccfyx(),
				SC.<A1, A2, A3>szzsccfyx(),
				SC.<A1, A2, A3>szzscc(),
				SC.<A1, A2, A3>sscscscscscc());
	}

	/* INTERCHANGE: 1 + 8 */
	static void testT(boolean inConcurrence)
	{
		assert SCTests.<String, Void, String>tt()
			.stream()
			.map(SCTests.<String, Void>ttMapper(".", null))
			.reduce(true,
				(left, right) -> left && ".".equals(right),
				uoeBOThrower());
		assert SCTests.<Void, String, Void>tt()
			.stream()
			.map(SCTests.<Void, String>ttMapper(null, "."))
			.reduce(true,
				(left, right) -> left && right == null,
				uoeBOThrower());
	}

	/* COMPOSITION: 1 + 5 */
	static void testZ(boolean inConcurrence)
	{
		final Set<Function<Function<String, String>,
				Function<Function<String, String>,
				Function<String, String>>>> zz = Set.of(
			Combinators.<String, String, String>z(),
			SC.<String, String, String>cfxgx(),
			SC.<String, String, String>scfgx(),
			SC.<String, String, String>csfcfgx(),
			SC.<String, String, String>scscfgx(),
			SC.<String, String, String>scsc());
		final Function<String, String> upperCaseMapper = text -> text
			.toUpperCase(Locale.ROOT);
		final Function<String, String> pairCodePointer = text -> text
			.codePoints()
			.limit(2L)
			.collect(StringBuilder::new,
				StringBuilder::appendCodePoint,
				uoeBCThrower())
			.toString();
		final String hallo = "hallo";
		final String result = upperCaseMapper
			.compose(pairCodePointer)
			.apply(hallo)
			.repeat(zz.size());
		assert result.equals(zz
			.stream()
			.map(Combinators.<Function<Function<String, String>,
					Function<Function<String, String>,
					Function<String,
					Function<Function<Function<String, String>,
						Function<Function<String, String>,
						Function<String, String>>>,
								String>>>>>i()
				.apply(f -> g -> x -> z -> z
					.apply(f)
					.apply(g)
					.apply(x))
				.apply(upperCaseMapper)
				.apply(pairCodePointer)
				.apply(hallo))
			.collect(StringBuilder::new,
				StringBuilder::append,
				uoeBCThrower())
			.toString());
	}

	/* DUPLICATOR: 1 + 13 */
	static void testW(boolean inConcurrence)
	{
		final int value = 2;
		final int quotient = 1;
		assert quotient == Set.of(Combinators.<Integer, Integer>w(),
				SC.<Integer, Integer>fxcxfx(),
				SC.<Integer, Integer>fxscfx(),
				SC.<Integer, Integer>sfscfx(),
				SC.<Integer, Integer>ssscfx(),
				SC.<Integer, Integer>sssc(),
				SC.<Integer, Integer>tfxx(),
				SC.<Integer, Integer>ttxfx(),
				SC.<Integer, Integer>tttxxf(),
				SC.<Integer, Integer>ttxttxf(),
				SC.<Integer, Integer>sttttxf(),
				SC.<Integer, Integer>tsttttfx(),
				SC.<Integer, Integer>tstttt(),
				SC.<Integer, Integer>
	sscscscscscc_s_sscscscscscc_sscscscscscc_sscscscscscc_sscscscscscc())
			.stream()
			.map(Combinators.<Function<Function<Integer,
						Function<Integer, Integer>>,
					Function<Integer,
					Function<Function<Function<Integer,
						Function<Integer, Integer>>,
							Function<Integer, Integer>>,
								Integer>>>>i()
				.apply(f -> x -> w -> w
					.apply(f)
					.apply(x))	// Smullyan's _vireo_.
				.apply(x -> y -> x / y)
				.apply(value))
			.reduce(quotient, (left, right) -> left * right);
	}

	static void runAll(boolean inConcurrence)
	{
		Combinators.<Function<Boolean,
				Function<Set<Consumer<Boolean>>,
					Stream<Consumer<Boolean>>>>>i()
			.apply(concurrent -> tests -> (concurrent)
				? tests.parallelStream()
				: tests.stream())
			.apply(inConcurrence)
			.apply(Set.of(concurrent -> testI(concurrent),
					concurrent -> testT(concurrent),
					concurrent -> testZ(concurrent),
					concurrent -> testW(concurrent)))
			.forEach(Combinators.<Function<Boolean,
					Consumer<Consumer<Boolean>>>>i()
				.apply(concurrent -> test -> test
					.accept(concurrent))
				.apply(inConcurrence));
	}
}
