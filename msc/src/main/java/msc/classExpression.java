package msc;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.*;

public class classExpression {

	public static void main(String[] args) throws OWLException {
		IRI IOR = IRI.create("http://owl.api.tutorial");
		OWLOntologyManager man = OWLManager.createOWLOntologyManager();
		OWLOntology o = man.createOntology(IOR);
		OWLDataFactory df = o.getOWLOntologyManager().getOWLDataFactory();
		OWLClass A = df.getOWLClass(IOR + "#A");
		OWLClass B = df.getOWLClass(IOR + "#B");
		OWLClass X = df.getOWLClass(IOR + "#X");
		OWLObjectProperty R = df.getOWLObjectProperty(IOR+"#R");
		OWLObjectProperty S = df.getOWLObjectProperty(IOR+"#S");
		OWLSubClassOfAxiom ax = df.getOWLSubClassOfAxiom(
				df.getOWLObjectSomeValuesFrom(R, A), 
				df.getOWLObjectSomeValuesFrom(S, B));
		o.add(ax);
		
		o.logicalAxioms().forEach(System.out::println);

	}

}
