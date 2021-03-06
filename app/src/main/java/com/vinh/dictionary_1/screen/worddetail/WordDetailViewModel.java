package com.vinh.dictionary_1.screen.worddetail;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.webkit.WebViewClient;
import com.vinh.dictionary_1.BR;
import com.vinh.dictionary_1.data.model.Word;

/**
 * Exposes the data to be used in the WordDetail screen.
 */

public class WordDetailViewModel extends BaseObservable implements WordDetailContract.ViewModel {

    private WordDetailContract.Presenter mPresenter;
    private Context mContext;
    private Word mWord;
    private String mWordDescription;
    private boolean mBookmarkState;
    private WebViewClient mWordDetailWebViewClient;

    public WordDetailViewModel(Context context) {
        mContext = context;
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
    }

    @Override
    public void setPresenter(WordDetailContract.Presenter presenter) {
        mPresenter = presenter;
        mWordDetailWebViewClient = new WordDetailPresenter.WordDetailWebViewClient(mContext,
                mPresenter.getDictRepository());
        boolean isWordBookmarked = mPresenter.isWordBookmarked(mWord.getWord());
        mWord.setBookmarked(isWordBookmarked);
        setBookmarkState(isWordBookmarked);
    }

    public Word getWord() {
        return mWord;
    }

    @Override
    public void setWord(Word word) {
        mWord = word;
        if (mWord.getEVDescription() != null) {
            mWordDescription = mWord.getEVDescription();
        } else {
            mWordDescription = mWord.getVEDescription();
        }
    }

    @Override
    public void speakUS(String word) {
        mPresenter.speakUS(word);
    }

    @Override
    public void speakUK(String word) {
        mPresenter.speakUK(word);
    }

    @Override
    public void onBookmarkIconTouch() {
        mPresenter.onBookmarkIconTouch(!mWord.isBookmarked());
        mWord.setBookmarked(!mWord.isBookmarked());
        setBookmarkState(mWord.isBookmarked());
    }

    @Bindable
    public boolean isBookmarkState() {
        return mBookmarkState;
    }

    public void setBookmarkState(boolean bookmarkState) {
        mBookmarkState = bookmarkState;
        notifyPropertyChanged(BR.bookmarkState);
    }

    public String getWordDescription() {
        return mWordDescription;
    }

    public void setWordDescription(String wordDescription) {
        mWordDescription = wordDescription;
    }

    @Bindable
    public WebViewClient getWordDetailWebViewClient() {
        return mWordDetailWebViewClient;
    }

    public void setWordDetailWebViewClient(
            WordDetailPresenter.WordDetailWebViewClient wordDetailWebViewClient) {
        mWordDetailWebViewClient = wordDetailWebViewClient;
        notifyPropertyChanged(BR.wordDetailWebViewClient);
    }
}
