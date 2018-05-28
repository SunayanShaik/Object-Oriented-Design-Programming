package edu.umb.cs.cs680.hw11;

public class VirusCheckingVisitor implements FSVisitor {

	int quarantined = 0;
	
	public void visit(Link link) {
		return;
	}

	public void visit(Directory dir) {
		return;
	}

	public void visit(File file) {
		if(file.isHasVirus()) {
			quarantined++;
		}
	}

	public int getQuarantinedNum() {
		return quarantined;
	}

}
