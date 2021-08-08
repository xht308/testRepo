package msc;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDeclarationAxiom;
import org.semanticweb.owlapi.model.OWLException;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;

public class classDeclaration {

	public static void main(String[] args) throws OWLException {
		IRI IOR = IRI.create("http://owl.api.tutorial");
		OWLOntologyManager man = OWLManager.createOWLOntologyManager();
		OWLOntology o = man.createOntology(IOR);
		OWLDataFactory df = o.getOWLOntologyManager().getOWLDataFactory();
		OWLClass person = df.getOWLClass(IOR+"#Person");
		OWLDeclarationAxiom da = df.getOWLDeclarationAxiom(person);
		o.add(da);
		System.out.println(o);

	}

}
