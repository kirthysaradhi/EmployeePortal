package com.employee.api.service;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.employee.api.model.DbSequence;

/**
 * 
 * @author Kirthy Saradhi Devarapalli The SequenceGeneratorService Class used to
 *         generate sequence for Employee ID
 */
@Service
public class SequenceGeneratorService {

	@Autowired
	private MongoOperations mongoOperations;

	public int sequenceGenerator(String sequenceName) {
		// Get Sequence Number
		Query query = new Query(Criteria.where("id").is(sequenceName));

		// Increment the Sequence Id
		Update update = new Update().inc("seq", 1);

		// Update in DB
		DbSequence dbSequence = mongoOperations.findAndModify(query, update, options().returnNew(true).upsert(true),
				DbSequence.class);
		return !Objects.isNull(dbSequence) ? dbSequence.getSeq() : 1;
	}
}
