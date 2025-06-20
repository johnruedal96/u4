package com.fundamentos.clud.u4.match.infrastructure.firebase;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Repository;

import com.fundamentos.clud.u4.match.domain.Match;
import com.fundamentos.clud.u4.match.domain.MatchRepository;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;

@Repository
public class FirebaseMatchRepository implements MatchRepository {

    private static final String COLLECTION_NAME = "jornadas_uefa";
    private final Firestore firestore = FirestoreClient.getFirestore();

    @Override
    public Match save(Match match) {
        try {
            firestore.collection(COLLECTION_NAME).document(match.getId()).set(match).get();
            return match;
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Error saving match to Firebase", e);
        }
    }

    @Override
    public Optional<Match> findById(String id) {
        try {
            var doc = firestore.collection(COLLECTION_NAME).document(id).get().get();
            if (doc.exists()) {
                return Optional.ofNullable(doc.toObject(Match.class));
            }
            return Optional.empty();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Error fetching match from Firebase", e);
        }
    }

    @Override
    public List<Match> findAll() {
        try {
            var querySnapshot = firestore.collection(COLLECTION_NAME).get().get();
            var matches = new ArrayList<Match>();
            for (var doc : querySnapshot.getDocuments()) {
                matches.add(doc.toObject(Match.class));
            }
            return matches;
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Error listing matches from Firebase", e);
        }
    }

    @Override
    public void deleteById(String id) {
        firestore.collection(COLLECTION_NAME).document(id).delete();
    }

    @Override
    public boolean exists(String id) {
        try {
            return firestore.collection(COLLECTION_NAME).document(id).get().get().exists();
        } catch (Exception e) {
            throw new RuntimeException("Error checking existence in Firebase", e);
        }
    }

    @Override
    public Match update(String id, Match match) {
        try {
            firestore.collection(COLLECTION_NAME).document(id).set(match).get();
            return match;
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Error saving match to Firebase", e);
        }
    }
}
