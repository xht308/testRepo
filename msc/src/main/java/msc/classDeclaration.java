package msc;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.*;

public class classDeclaration {

	public static void main(String[] args) throws OWLException {
		IRI IOR = IRI.create("http://owl.api.tutorial");
		OWLOntologyManager man = OWLManager.createOWLOntologyManager();
		OWLOntology o = man.createOntology(IOR);
		OWLDataFactory df = o.getOWLOntologyManager().getOWLDataFactory();
		OWLClass person = df.getOWLClass(IOR+"#Person");
		OWLClass woman = df.getOWLClass(IOR+"#Woman");
		OWLDeclarationAxiom da = df.getOWLDeclarationAxiom(person);
		OWLSubClassOfAxiom w_sub_p = df.getOWLSubClassOfAxiom(woman, person);
		o.add(da);
		o.add(w_sub_p);
		System.out.println(o);

	}

}
