package org.function;

class SCTester
{
	static {
		SCTester.class
			.getClassLoader()
			.setClassAssertionStatus("org.function.SCTests",
								true);
	}

	private SCTester() { /* No instantiation. */ }

	public static void main(String[] args)
	{
		SCTests.runAll(args.length > 0 && "-c".equals(args[0]));
	}
}
