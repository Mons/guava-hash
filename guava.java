public class guava {
	// Reference implementation, taken from:
	// https://code.google.com/p/guava-libraries/source/browse/guava/src/com/google/common/hash/Hashing.java
	
	private static final class LinearCongruentialGenerator {
		private long state;
		public LinearCongruentialGenerator(long seed) {
			this.state = seed;
		}
		public double nextDouble() {
			state = 2862933555777941757L * state + 1;
			return
				(
					(double) (
						(int) (state >>> 33) + 1
					)
				) / (0x1.0p31);
		}
	}
	
	public static int consistentHash(long input, int buckets) {
		LinearCongruentialGenerator generator = new LinearCongruentialGenerator(input);
		int candidate = 0;
		int next;
		while (true) {
			next = (int) ( (candidate + 1) / generator.nextDouble() );
			if (next >= 0 && next < buckets) {
				candidate = next;
			} else {
				return candidate;
			}
		}
	}

	public static void main(String[] args) {
		// Generate test set
		long values[] = { 1,2,3,34,-19,-199999999 };
		int buckets[] = { 1,2,4,8,50,100,1000,1024,1025, 0xffffffff };
		int k,l;
		for (k=0; k < values.length; k++) {
			for (l=0; l<buckets.length; l++) {
				System.out.println("\t{"+values[k]+", "+buckets[l]+", " + consistentHash(values[k], buckets[l]) + "},");
			}
		}
	}
}