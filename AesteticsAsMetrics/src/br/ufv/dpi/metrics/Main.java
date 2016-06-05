package br.ufv.dpi.metrics;

public class Main {

	public static void main(String args []) {
		ComputeMetrics m = new ComputeMetrics();
		for(int i=5;i<30;i++)
		{
			m.compute();
			m.printMetrics();
		}
		//m.printLabels();
	}
	
}
