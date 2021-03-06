#include <stdio.h>
#include <stdint.h>
#include "guava.h"

static const long long
	K   = 2862933555777941757L;

static const double
	D   = 0x1.0p31;

int guava(long long state, unsigned int buckets) {
	int candidate = 0;
	int next;
	while (1) {
		state = K * state + 1;
		next = (int) ( (double) (candidate + 1) / ( (double)( (int)( (long long unsigned) state >> 33 ) + 1 ) / D ) );
		if ( ( next >= 0 ) && ( next < buckets )) {
			candidate = next;
		} else {
			return candidate;
		}
	}
}
