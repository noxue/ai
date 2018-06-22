package com.ai.dao;

import com.ai.domain.bo.Voice;

import java.util.List;

public interface VoiceDao extends VoiceMapper{
    public List<Voice> select();
}