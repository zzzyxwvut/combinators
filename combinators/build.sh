#!/bin/sh -e
#
# Shell dependencies (packaging): cp, mkdir (of coreutils); find (of findutils).
#
# Compile, test, and package "org.function" classes.

agenda=1				# Compilation only.

case "$1" in
-h | --help)
	echo >&2 "Usage: $0 [test | package] [arguments...]"
	exit 2
	;;
test)	agenda=$((${agenda} | 2))	# Compilation and testing.
	shift
	;;
package)
	agenda=$((${agenda} | 2 | 4))	# Compilation and testing and packaging.
	shift
esac

set +f					# Enable pathname expansion.
src=src
bin=bin
test -x ${src}/ || exit 4		# Check the current working directory.
javac -d ${bin}/ -Xdiags:verbose -Xlint ${src}/org/function/*.java

test $((${agenda} & 2)) -ne 0 || exit 0
echo >&2 "TESTING..."
java -cp ${bin}/ -XX:+UnlockExperimentalVMOptions -XX:+UseEpsilonGC \
	-XX:+AlwaysPreTouch -Xms4m -Xmx4m -Xlog:heap\*=info \
	org.function.SCTester "$@"

test $((${agenda} & 4)) -ne 0 || exit 0
echo >&2 "PACKAGING..."
test -x "`command -v cp`" || exit 16
test -x "`command -v find`" || exit 16
test -x "`command -v mkdir`" || exit 16
tmp=tmp
meta_inf=META-INF
test -d ${tmp} || mkdir ${tmp}
test -d ${bin}/${meta_inf} || mkdir -p ${bin}/${meta_inf}
cp -p -t ${bin}/${meta_inf}/ ../LICENSE
cd ${bin}
jar -cfv ../${tmp}/combinators.jar \
	${meta_inf}/LICENSE \
	`find org/function -iname \*.class \
		\! \( -iname \*Tests.class -o -iname \*Tester.class \) -type f`
