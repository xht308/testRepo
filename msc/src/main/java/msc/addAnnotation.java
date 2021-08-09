package msc;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.*;

public class addAnnotation {

	public static void main(String[] args) throws OWLException {
		OWLOntologyManager man = OWLManager.createOWLOntologyManager();
		IRI IOR = IRI.create("http://owl.api.tutorial");
		OWLOntology ont = man.createOntology(IOR);
		OWLDataFactory df = ont.getOWLOntologyManager().getOWLDataFactory();
		OWLClass student = df.getOWLClass(IRI.create(IOR+"#ID879812719872"));
		OWLAnnotation commentAnno = df.getOWLAnnotation(df.getRDFSComment(), df.getOWLLiteral("Class representing all Students in the University", "en"));
		OWLAnnotation labelAnno = df.getOWLAnnotation(df.getRDFSLabel(), df.getOWLLiteral("Student", "en"));
		OWLAxiom ax1 = df.getOWLAnnotationAssertionAxiom(student.getIRI(), labelAnno);
		OWLAxiom ax2 = df.getOWLAnnotationAssertionAxiom(student.getIRI(), commentAnno);
		man.applyChange(new AddAxiom(ont, ax1));
		man.applyChange(new AddAxiom(ont, ax2));
		System.out.println(ont);
		

	}

}
