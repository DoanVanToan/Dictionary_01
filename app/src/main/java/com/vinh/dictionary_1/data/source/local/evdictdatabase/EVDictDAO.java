package com.vinh.dictionary_1.data.source.local.evdictdatabase;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;
import com.vinh.dictionary_1.data.model.Word;
import io.reactivex.Flowable;
import java.util.List;

/**
 * Created by VinhTL on 27/10/2017.
 */

@Dao
public interface EVDictDAO {
    @Query("SELECT word ,av  ,dnpn , mean FROM word_tbl WHERE word >= :queryWord LIMIT :limitCount")
    Flowable<List<Word>> getEVWordsDetail(String queryWord, int limitCount);

    @Query("SELECT word ,av  ,dnpn , mean FROM word_tbl WHERE word = :queryWord LIMIT 1")
    Flowable<Word> getEVWordDetail(String queryWord);

    @Query("SELECT word ,av  ,dnpn , mean FROM word_tbl WHERE word = :queryWord LIMIT 1")
    Word getEVWordDetailSync(String queryWord);

    @Query("SELECT word ,av ,dnpn ,mean FROM word_tbl ORDER BY RANDOM() LIMIT 1")
    Flowable<Word> getEVRandomWord();
}
