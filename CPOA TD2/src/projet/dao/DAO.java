package projet.dao;

import java.util.ArrayList;

import projet.metier.Client;

public interface DAO <T> {

	boolean create(T objet);

	boolean update(T objet);

	boolean delete(T objet);

	T getById(int id);

	ArrayList<T> findAll();
}
