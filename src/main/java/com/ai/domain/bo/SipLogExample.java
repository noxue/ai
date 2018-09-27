package com.ai.domain.bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class SipLogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SipLogExample() {
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

        public Criteria andAccepterIsNull() {
            addCriterion("accepter is null");
            return (Criteria) this;
        }

        public Criteria andAccepterIsNotNull() {
            addCriterion("accepter is not null");
            return (Criteria) this;
        }

        public Criteria andAccepterEqualTo(String value) {
            addCriterion("accepter =", value, "accepter");
            return (Criteria) this;
        }

        public Criteria andAccepterNotEqualTo(String value) {
            addCriterion("accepter <>", value, "accepter");
            return (Criteria) this;
        }

        public Criteria andAccepterGreaterThan(String value) {
            addCriterion("accepter >", value, "accepter");
            return (Criteria) this;
        }

        public Criteria andAccepterGreaterThanOrEqualTo(String value) {
            addCriterion("accepter >=", value, "accepter");
            return (Criteria) this;
        }

        public Criteria andAccepterLessThan(String value) {
            addCriterion("accepter <", value, "accepter");
            return (Criteria) this;
        }

        public Criteria andAccepterLessThanOrEqualTo(String value) {
            addCriterion("accepter <=", value, "accepter");
            return (Criteria) this;
        }

        public Criteria andAccepterLike(String value) {
            addCriterion("accepter like", value, "accepter");
            return (Criteria) this;
        }

        public Criteria andAccepterNotLike(String value) {
            addCriterion("accepter not like", value, "accepter");
            return (Criteria) this;
        }

        public Criteria andAccepterIn(List<String> values) {
            addCriterion("accepter in", values, "accepter");
            return (Criteria) this;
        }

        public Criteria andAccepterNotIn(List<String> values) {
            addCriterion("accepter not in", values, "accepter");
            return (Criteria) this;
        }

        public Criteria andAccepterBetween(String value1, String value2) {
            addCriterion("accepter between", value1, value2, "accepter");
            return (Criteria) this;
        }

        public Criteria andAccepterNotBetween(String value1, String value2) {
            addCriterion("accepter not between", value1, value2, "accepter");
            return (Criteria) this;
        }

        public Criteria andHandlerIsNull() {
            addCriterion("`handler` is null");
            return (Criteria) this;
        }

        public Criteria andHandlerIsNotNull() {
            addCriterion("`handler` is not null");
            return (Criteria) this;
        }

        public Criteria andHandlerEqualTo(String value) {
            addCriterion("`handler` =", value, "handler");
            return (Criteria) this;
        }

        public Criteria andHandlerNotEqualTo(String value) {
            addCriterion("`handler` <>", value, "handler");
            return (Criteria) this;
        }

        public Criteria andHandlerGreaterThan(String value) {
            addCriterion("`handler` >", value, "handler");
            return (Criteria) this;
        }

        public Criteria andHandlerGreaterThanOrEqualTo(String value) {
            addCriterion("`handler` >=", value, "handler");
            return (Criteria) this;
        }

        public Criteria andHandlerLessThan(String value) {
            addCriterion("`handler` <", value, "handler");
            return (Criteria) this;
        }

        public Criteria andHandlerLessThanOrEqualTo(String value) {
            addCriterion("`handler` <=", value, "handler");
            return (Criteria) this;
        }

        public Criteria andHandlerLike(String value) {
            addCriterion("`handler` like", value, "handler");
            return (Criteria) this;
        }

        public Criteria andHandlerNotLike(String value) {
            addCriterion("`handler` not like", value, "handler");
            return (Criteria) this;
        }

        public Criteria andHandlerIn(List<String> values) {
            addCriterion("`handler` in", values, "handler");
            return (Criteria) this;
        }

        public Criteria andHandlerNotIn(List<String> values) {
            addCriterion("`handler` not in", values, "handler");
            return (Criteria) this;
        }

        public Criteria andHandlerBetween(String value1, String value2) {
            addCriterion("`handler` between", value1, value2, "handler");
            return (Criteria) this;
        }

        public Criteria andHandlerNotBetween(String value1, String value2) {
            addCriterion("`handler` not between", value1, value2, "handler");
            return (Criteria) this;
        }

        public Criteria andNumIsNull() {
            addCriterion("num is null");
            return (Criteria) this;
        }

        public Criteria andNumIsNotNull() {
            addCriterion("num is not null");
            return (Criteria) this;
        }

        public Criteria andNumEqualTo(String value) {
            addCriterion("num =", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotEqualTo(String value) {
            addCriterion("num <>", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumGreaterThan(String value) {
            addCriterion("num >", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumGreaterThanOrEqualTo(String value) {
            addCriterion("num >=", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumLessThan(String value) {
            addCriterion("num <", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumLessThanOrEqualTo(String value) {
            addCriterion("num <=", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumLike(String value) {
            addCriterion("num like", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotLike(String value) {
            addCriterion("num not like", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumIn(List<String> values) {
            addCriterion("num in", values, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotIn(List<String> values) {
            addCriterion("num not in", values, "num");
            return (Criteria) this;
        }

        public Criteria andNumBetween(String value1, String value2) {
            addCriterion("num between", value1, value2, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotBetween(String value1, String value2) {
            addCriterion("num not between", value1, value2, "num");
            return (Criteria) this;
        }

        public Criteria andResultIsNull() {
            addCriterion("`result` is null");
            return (Criteria) this;
        }

        public Criteria andResultIsNotNull() {
            addCriterion("`result` is not null");
            return (Criteria) this;
        }

        public Criteria andResultEqualTo(Long value) {
            addCriterion("`result` =", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultNotEqualTo(Long value) {
            addCriterion("`result` <>", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultGreaterThan(Long value) {
            addCriterion("`result` >", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultGreaterThanOrEqualTo(Long value) {
            addCriterion("`result` >=", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultLessThan(Long value) {
            addCriterion("`result` <", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultLessThanOrEqualTo(Long value) {
            addCriterion("`result` <=", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultIn(List<Long> values) {
            addCriterion("`result` in", values, "result");
            return (Criteria) this;
        }

        public Criteria andResultNotIn(List<Long> values) {
            addCriterion("`result` not in", values, "result");
            return (Criteria) this;
        }

        public Criteria andResultBetween(Long value1, Long value2) {
            addCriterion("`result` between", value1, value2, "result");
            return (Criteria) this;
        }

        public Criteria andResultNotBetween(Long value1, Long value2) {
            addCriterion("`result` not between", value1, value2, "result");
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