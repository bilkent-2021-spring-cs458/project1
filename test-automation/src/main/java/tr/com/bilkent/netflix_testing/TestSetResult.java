package tr.com.bilkent.netflix_testing;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TestSetResult {
	private int numPassed;
	private int total;

	public void incrementPassedCases() {
		numPassed++;
	}

	@Override
	public String toString() {
		return "Passed: " + numPassed + " \tFailed: " + (total - numPassed) + " \tTotal: " + total + " \tSuccess rate: "
				+ String.format("%.2f", 1.0 * numPassed / total);
	}
}
