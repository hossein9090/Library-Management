package ir.hossein.util;

import ir.hossein.repository.BookRepository;

public class ApplicationContext {
    public Databaseutil connections;
    public BookRepository bookRepository;


    public ApplicationContext() {
        this.connections = new Databaseutil();
    }
}
