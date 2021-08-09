package msc;

import org.semanticweb.HermiT.ReasonerFactory;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.reasoner.*;

public class Task5 {

	public static void main(String[] args) throws OWLException {
		//Create a new ontology
		OWLOntologyManager man = OWLManager.createOWLOntologyManager();
		IRI IOR = IRI.create("http://owl.api.tutorial");
		OWLOntology ont = man.createOntology(IOR);
		OWLDataFactory df = man.getOWLDataFactory();
		//Create two disjoint classes and another class as a subclass of their intersection
		OWLClass student = df.getOWLClass(IRI.create(IOR+"#Student"));
		OWLClass teacher = df.getOWLClass(IRI.create(IOR+"#Teacher"));
		OWLClass demonstrator = df.getOWLClass(IRI.create(IOR+"#Demonstrator"));
		OWLAxiom ax = df.getOWLSubClassOfAxiom(demonstrator, df.getOWLObjectIntersectionOf(student,teacher));
		ont.add(ax);
		//Instantiate a reasoner and determine whether the ontology is consistent
		//Print the list of unsatisfiable classes to the console
		OWLReasonerFactory rf = new ReasonerFactory();
		OWLReasoner r = rf.createReasoner(ont);
		r.precomputeInferences(InferenceType.CLASS_HIERARCHY);
//		boolean flag = true;
//		for (OWLClass owlClass:ont.getClassesInSignature()) {
//			if (!r.isSatisfiable(owlClass)) {
//				if (flag) {
//					flag = false;
//					System.out.println("The ontology is not consistent.");
//				}
//				System.out.println(owlClass);
//			}
//			System.out.println(owlClass + " Checked");
//		}
//		if (flag) System.out.println("The ontology is consistent.");
		if (r.isConsistent()) System.out.println("The ontology is consistent.");
		else System.out.println("The ontology is not consistent.");
		r.getUnsatisfiableClasses().forEach(System.out::println);
		//Create an individual and make it an instance of the unsatisfiable class
		OWLNamedIndividual ind = df.getOWLNamedIndividual(IRI.create(IOR+"#Ind"));
		for (OWLClass owlClass:r.getUnsatisfiableClasses()) {
			ont.add(df.getOWLClassAssertionAxiom(owlClass, ind));
		}
		//Check whether the ontology is consistent 
		if (r.isConsistent()) System.out.println("The ontology is consistent.");
		else System.out.println("The ontology is not consistent.");
		r.flush();
		if (r.isConsistent()) System.out.println("The ontology is consistent.");
		else System.out.println("The ontology is not consistent.");

	}

}
