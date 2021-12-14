package com.bbp.bbptest.google.config;

public class GAQLQuery {

    public static final String TOP_LEVEL_USER_INTEREST_QUERY = "SELECT user_interest.user_interest_parent, user_interest.resource_name, user_interest.user_interest_id, user_interest.taxonomy_type, user_interest.name, user_interest.launched_to_all, user_interest.availabilities FROM user_interest WHERE user_interest.user_interest_parent IS NULL";
    public static final String CHILD_USER_INTEREST_QUERY = "SELECT user_interest.user_interest_parent, user_interest.resource_name, user_interest.user_interest_id, user_interest.taxonomy_type, user_interest.name, user_interest.launched_to_all, user_interest.availabilities FROM user_interest WHERE user_interest.user_interest_parent = '?'";

    public static final String TOP_LEVEL_TOPIC_QUERY = "SELECT topic_constant.topic_constant_parent, topic_constant.resource_name, topic_constant.path, topic_constant.id FROM topic_constant WHERE topic_constant.topic_constant_parent is null";
    public static final String CHILD_TOPIC_QUERY = "SELECT topic_constant.topic_constant_parent, topic_constant.resource_name, topic_constant.path, topic_constant.id FROM topic_constant WHERE topic_constant.topic_constant_parent = '?'";

}
