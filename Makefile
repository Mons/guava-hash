o:
	gcc -c -Wall -Werror -fpic guava.c

so:
	gcc -shared -o libguava.so guava.o

main:
	gcc -L. -Wl,-rpath=. -Wall -o test main.c -lguava

all: o so main
