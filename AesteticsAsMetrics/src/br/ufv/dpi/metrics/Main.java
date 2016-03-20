package br.ufv.dpi.metrics;

public class Main {

	public static void main(String args []) {
		ComputeMetrics m = new ComputeMetrics();
		m.compute();
		m.printMetrics();
		m.printLabels();
	}
	
}
