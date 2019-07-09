package com.example.demo;



/**


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;


@Service
public class MongoTraceRepository implements HttpTraceRepository{
    
    @Autowired
    private MongoOperations mongoOps;

    @Override
    public List<HttpTrace> findAll() {
        return mongoOps.findAll(HttpTrace.class);
    }

    @Override
    public void add(HttpTrace trace) {
        mongoOps.save(trace);
    }

}
**/
