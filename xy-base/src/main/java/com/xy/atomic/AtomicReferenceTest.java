package com.xy.atomic;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceTest {

    public static AtomicReference<User> userAtomicReference = new AtomicReference<>();

    public static void main(String[] args) {
        User user = new User("conan",15);
        userAtomicReference.set(user);
        User updateUser = new User("Shinichi",17);
        userAtomicReference.compareAndSet(user,updateUser);
        System.out.println(userAtomicReference.get().getName());
        System.out.println(userAtomicReference.get().getOld());
    }

    static class User{
        private String name;
        private int old;

        public String getName() {
            return name;
        }

        public int getOld() {
            return old;
        }

        public User(String name, int old) {
            this.name = name;
            this.old = old;
        }
    }
}
