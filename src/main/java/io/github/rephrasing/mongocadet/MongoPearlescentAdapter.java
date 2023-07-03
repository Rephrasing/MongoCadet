package io.github.rephrasing.mongocadet;

import io.github.rephrasing.pearlescent.PearlescentCacheAdapter;
import org.bson.Document;

public abstract class MongoPearlescentAdapter<T> extends PearlescentCacheAdapter<T, Document> {

    @Override
    public Class<Document> getSerializeType() {
        return Document.class;
    }
}
