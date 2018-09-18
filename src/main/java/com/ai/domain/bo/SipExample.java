package com.ai.domain.bo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class SipExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SipExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("`name` is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("`name` is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("`name` =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("`name` <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("`name` >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("`name` >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("`name` <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("`name` <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("`name` like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("`name` not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("`name` in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("`name` not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("`name` between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("`name` not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNull() {
            addCriterion("username is null");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNotNull() {
            addCriterion("username is not null");
            return (Criteria) this;
        }

        public Criteria andUsernameEqualTo(String value) {
            addCriterion("username =", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotEqualTo(String value) {
            addCriterion("username <>", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThan(String value) {
            addCriterion("username >", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThanOrEqualTo(String value) {
            addCriterion("username >=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThan(String value) {
            addCriterion("username <", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThanOrEqualTo(String value) {
            addCriterion("username <=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLike(String value) {
            addCriterion("username like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotLike(String value) {
            addCriterion("username not like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameIn(List<String> values) {
            addCriterion("username in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotIn(List<String> values) {
            addCriterion("username not in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameBetween(String value1, String value2) {
            addCriterion("username between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotBetween(String value1, String value2) {
            addCriterion("username not between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNull() {
            addCriterion("`password` is null");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNotNull() {
            addCriterion("`password` is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordEqualTo(String value) {
            addCriterion("`password` =", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotEqualTo(String value) {
            addCriterion("`password` <>", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThan(String value) {
            addCriterion("`password` >", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("`password` >=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThan(String value) {
            addCriterion("`password` <", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThanOrEqualTo(String value) {
            addCriterion("`password` <=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLike(String value) {
            addCriterion("`password` like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotLike(String value) {
            addCriterion("`password` not like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordIn(List<String> values) {
            addCriterion("`password` in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotIn(List<String> values) {
            addCriterion("`password` not in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordBetween(String value1, String value2) {
            addCriterion("`password` between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotBetween(String value1, String value2) {
            addCriterion("`password` not between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andHostIsNull() {
            addCriterion("`host` is null");
            return (Criteria) this;
        }

        public Criteria andHostIsNotNull() {
            addCriterion("`host` is not null");
            return (Criteria) this;
        }

        public Criteria andHostEqualTo(String value) {
            addCriterion("`host` =", value, "host");
            return (Criteria) this;
        }

        public Criteria andHostNotEqualTo(String value) {
            addCriterion("`host` <>", value, "host");
            return (Criteria) this;
        }

        public Criteria andHostGreaterThan(String value) {
            addCriterion("`host` >", value, "host");
            return (Criteria) this;
        }

        public Criteria andHostGreaterThanOrEqualTo(String value) {
            addCriterion("`host` >=", value, "host");
            return (Criteria) this;
        }

        public Criteria andHostLessThan(String value) {
            addCriterion("`host` <", value, "host");
            return (Criteria) this;
        }

        public Criteria andHostLessThanOrEqualTo(String value) {
            addCriterion("`host` <=", value, "host");
            return (Criteria) this;
        }

        public Criteria andHostLike(String value) {
            addCriterion("`host` like", value, "host");
            return (Criteria) this;
        }

        public Criteria andHostNotLike(String value) {
            addCriterion("`host` not like", value, "host");
            return (Criteria) this;
        }

        public Criteria andHostIn(List<String> values) {
            addCriterion("`host` in", values, "host");
            return (Criteria) this;
        }

        public Criteria andHostNotIn(List<String> values) {
            addCriterion("`host` not in", values, "host");
            return (Criteria) this;
        }

        public Criteria andHostBetween(String value1, String value2) {
            addCriterion("`host` between", value1, value2, "host");
            return (Criteria) this;
        }

        public Criteria andHostNotBetween(String value1, String value2) {
            addCriterion("`host` not between", value1, value2, "host");
            return (Criteria) this;
        }

        public Criteria andChargedIsNull() {
            addCriterion("charged is null");
            return (Criteria) this;
        }

        public Criteria andChargedIsNotNull() {
            addCriterion("charged is not null");
            return (Criteria) this;
        }

        public Criteria andChargedEqualTo(BigDecimal value) {
            addCriterion("charged =", value, "charged");
            return (Criteria) this;
        }

        public Criteria andChargedNotEqualTo(BigDecimal value) {
            addCriterion("charged <>", value, "charged");
            return (Criteria) this;
        }

        public Criteria andChargedGreaterThan(BigDecimal value) {
            addCriterion("charged >", value, "charged");
            return (Criteria) this;
        }

        public Criteria andChargedGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("charged >=", value, "charged");
            return (Criteria) this;
        }

        public Criteria andChargedLessThan(BigDecimal value) {
            addCriterion("charged <", value, "charged");
            return (Criteria) this;
        }

        public Criteria andChargedLessThanOrEqualTo(BigDecimal value) {
            addCriterion("charged <=", value, "charged");
            return (Criteria) this;
        }

        public Criteria andChargedIn(List<BigDecimal> values) {
            addCriterion("charged in", values, "charged");
            return (Criteria) this;
        }

        public Criteria andChargedNotIn(List<BigDecimal> values) {
            addCriterion("charged not in", values, "charged");
            return (Criteria) this;
        }

        public Criteria andChargedBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("charged between", value1, value2, "charged");
            return (Criteria) this;
        }

        public Criteria andChargedNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("charged not between", value1, value2, "charged");
            return (Criteria) this;
        }

        public Criteria andCreateatIsNull() {
            addCriterion("createAt is null");
            return (Criteria) this;
        }

        public Criteria andCreateatIsNotNull() {
            addCriterion("createAt is not null");
            return (Criteria) this;
        }

        public Criteria andCreateatEqualTo(Date value) {
            addCriterionForJDBCDate("createAt =", value, "createat");
            return (Criteria) this;
        }

        public Criteria andCreateatNotEqualTo(Date value) {
            addCriterionForJDBCDate("createAt <>", value, "createat");
            return (Criteria) this;
        }

        public Criteria andCreateatGreaterThan(Date value) {
            addCriterionForJDBCDate("createAt >", value, "createat");
            return (Criteria) this;
        }

        public Criteria andCreateatGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("createAt >=", value, "createat");
            return (Criteria) this;
        }

        public Criteria andCreateatLessThan(Date value) {
            addCriterionForJDBCDate("createAt <", value, "createat");
            return (Criteria) this;
        }

        public Criteria andCreateatLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("createAt <=", value, "createat");
            return (Criteria) this;
        }

        public Criteria andCreateatIn(List<Date> values) {
            addCriterionForJDBCDate("createAt in", values, "createat");
            return (Criteria) this;
        }

        public Criteria andCreateatNotIn(List<Date> values) {
            addCriterionForJDBCDate("createAt not in", values, "createat");
            return (Criteria) this;
        }

        public Criteria andCreateatBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("createAt between", value1, value2, "createat");
            return (Criteria) this;
        }

        public Criteria andCreateatNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("createAt not between", value1, value2, "createat");
            return (Criteria) this;
        }

        public Criteria andFirmsIsNull() {
            addCriterion("firms is null");
            return (Criteria) this;
        }

        public Criteria andFirmsIsNotNull() {
            addCriterion("firms is not null");
            return (Criteria) this;
        }

        public Criteria andFirmsEqualTo(String value) {
            addCriterion("firms =", value, "firms");
            return (Criteria) this;
        }

        public Criteria andFirmsNotEqualTo(String value) {
            addCriterion("firms <>", value, "firms");
            return (Criteria) this;
        }

        public Criteria andFirmsGreaterThan(String value) {
            addCriterion("firms >", value, "firms");
            return (Criteria) this;
        }

        public Criteria andFirmsGreaterThanOrEqualTo(String value) {
            addCriterion("firms >=", value, "firms");
            return (Criteria) this;
        }

        public Criteria andFirmsLessThan(String value) {
            addCriterion("firms <", value, "firms");
            return (Criteria) this;
        }

        public Criteria andFirmsLessThanOrEqualTo(String value) {
            addCriterion("firms <=", value, "firms");
            return (Criteria) this;
        }

        public Criteria andFirmsLike(String value) {
            addCriterion("firms like", value, "firms");
            return (Criteria) this;
        }

        public Criteria andFirmsNotLike(String value) {
            addCriterion("firms not like", value, "firms");
            return (Criteria) this;
        }

        public Criteria andFirmsIn(List<String> values) {
            addCriterion("firms in", values, "firms");
            return (Criteria) this;
        }

        public Criteria andFirmsNotIn(List<String> values) {
            addCriterion("firms not in", values, "firms");
            return (Criteria) this;
        }

        public Criteria andFirmsBetween(String value1, String value2) {
            addCriterion("firms between", value1, value2, "firms");
            return (Criteria) this;
        }

        public Criteria andFirmsNotBetween(String value1, String value2) {
            addCriterion("firms not between", value1, value2, "firms");
            return (Criteria) this;
        }

        public Criteria andMaxthreadIsNull() {
            addCriterion("maxThread is null");
            return (Criteria) this;
        }

        public Criteria andMaxthreadIsNotNull() {
            addCriterion("maxThread is not null");
            return (Criteria) this;
        }

        public Criteria andMaxthreadEqualTo(Integer value) {
            addCriterion("maxThread =", value, "maxthread");
            return (Criteria) this;
        }

        public Criteria andMaxthreadNotEqualTo(Integer value) {
            addCriterion("maxThread <>", value, "maxthread");
            return (Criteria) this;
        }

        public Criteria andMaxthreadGreaterThan(Integer value) {
            addCriterion("maxThread >", value, "maxthread");
            return (Criteria) this;
        }

        public Criteria andMaxthreadGreaterThanOrEqualTo(Integer value) {
            addCriterion("maxThread >=", value, "maxthread");
            return (Criteria) this;
        }

        public Criteria andMaxthreadLessThan(Integer value) {
            addCriterion("maxThread <", value, "maxthread");
            return (Criteria) this;
        }

        public Criteria andMaxthreadLessThanOrEqualTo(Integer value) {
            addCriterion("maxThread <=", value, "maxthread");
            return (Criteria) this;
        }

        public Criteria andMaxthreadIn(List<Integer> values) {
            addCriterion("maxThread in", values, "maxthread");
            return (Criteria) this;
        }

        public Criteria andMaxthreadNotIn(List<Integer> values) {
            addCriterion("maxThread not in", values, "maxthread");
            return (Criteria) this;
        }

        public Criteria andMaxthreadBetween(Integer value1, Integer value2) {
            addCriterion("maxThread between", value1, value2, "maxthread");
            return (Criteria) this;
        }

        public Criteria andMaxthreadNotBetween(Integer value1, Integer value2) {
            addCriterion("maxThread not between", value1, value2, "maxthread");
            return (Criteria) this;
        }

        public Criteria andCurrentthreadIsNull() {
            addCriterion("currentThread is null");
            return (Criteria) this;
        }

        public Criteria andCurrentthreadIsNotNull() {
            addCriterion("currentThread is not null");
            return (Criteria) this;
        }

        public Criteria andCurrentthreadEqualTo(Integer value) {
            addCriterion("currentThread =", value, "currentthread");
            return (Criteria) this;
        }

        public Criteria andCurrentthreadNotEqualTo(Integer value) {
            addCriterion("currentThread <>", value, "currentthread");
            return (Criteria) this;
        }

        public Criteria andCurrentthreadGreaterThan(Integer value) {
            addCriterion("currentThread >", value, "currentthread");
            return (Criteria) this;
        }

        public Criteria andCurrentthreadGreaterThanOrEqualTo(Integer value) {
            addCriterion("currentThread >=", value, "currentthread");
            return (Criteria) this;
        }

        public Criteria andCurrentthreadLessThan(Integer value) {
            addCriterion("currentThread <", value, "currentthread");
            return (Criteria) this;
        }

        public Criteria andCurrentthreadLessThanOrEqualTo(Integer value) {
            addCriterion("currentThread <=", value, "currentthread");
            return (Criteria) this;
        }

        public Criteria andCurrentthreadIn(List<Integer> values) {
            addCriterion("currentThread in", values, "currentthread");
            return (Criteria) this;
        }

        public Criteria andCurrentthreadNotIn(List<Integer> values) {
            addCriterion("currentThread not in", values, "currentthread");
            return (Criteria) this;
        }

        public Criteria andCurrentthreadBetween(Integer value1, Integer value2) {
            addCriterion("currentThread between", value1, value2, "currentthread");
            return (Criteria) this;
        }

        public Criteria andCurrentthreadNotBetween(Integer value1, Integer value2) {
            addCriterion("currentThread not between", value1, value2, "currentthread");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}