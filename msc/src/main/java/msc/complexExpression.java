package msc;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.*;

public class complexExpression {

	public static void main(String[] args) throws OWLException {
		//Create a new ontology
		OWLOntologyManager man = OWLManager.createOWLOntologyManager();
		IRI IOR = IRI.create("http://owl.api.tutorial");
		OWLOntology ont = man.createOntology(IOR);
		OWLDataFactory df = ont.getOWLOntologyManager().getOWLDataFactory();
		//Prepare classes and object property
		OWLClass student = df.getOWLClass(IRI.create(IOR+"#Student"));
		OWLClass person = df.getOWLClass(IRI.create(IOR+"#Person"));
		OWLClass university = df.getOWLClass(IRI.create(IOR+"#University"));
		OWLClass course = df.getOWLClass(IRI.create(IOR+"#Course"));
		OWLObjectProperty isEnrolledIn = df.getOWLObjectProperty(IOR+"#isEnrolledIn");
		OWLObjectProperty attends = df.getOWLObjectProperty(IOR+"#attends");
		//Add equivalent classes axiom
		OWLEquivalentClassesAxiom ax = df.getOWLEquivalentClassesAxiom(student, person,df.getOWLObjectIntersectionOf(df.getOWLObjectSomeValuesFrom(isEnrolledIn, university),df.getOWLObjectSomeValuesFrom(attends, course)));
		ont.add(ax);
		//Add named individuals
		OWLNamedIndividual X = df.getOWLNamedIndividual(IRI.create(IOR+"#X"));
		ont.add(df.getOWLClassAssertionAxiom(person, X));
		OWLNamedIndividual WKU = df.getOWLNamedIndividual(IRI.create(IOR+"#WKU"));
		ont.add(df.getOWLClassAssertionAxiom(university, WKU));
		//Property assertion
		ont.add(df.getOWLObjectPropertyAssertionAxiom(isEnrolledIn, X, WKU));
		ont.add(df.getOWLObjectPropertyAssertionAxiom(attends, X, df.getOWLAnonymousIndividual())); //Not sure
		//print logical axioms
		ont.logicalAxioms().forEach(System.out::println);
		

	}

}
