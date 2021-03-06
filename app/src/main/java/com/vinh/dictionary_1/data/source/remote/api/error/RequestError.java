package com.vinh.dictionary_1.data.source.remote.api.error;

import android.util.Log;
import com.google.gson.Gson;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by Sun on 4/16/2017.
 */

public abstract class RequestError implements Consumer<Throwable> {

    /**
     * Don't override this method.
     * Override {@link RequestError#onRequestError(BaseException)} instead
     */
    @Override
    public void accept(@NonNull Throwable throwable) throws Exception {
        Log.d("wtf", new Gson().toJson(throwable));
        if (throwable == null) {
            onRequestError(BaseException.toUnexpectedError(new Throwable("Unknown exception")));
            return;
        }
        if (throwable instanceof BaseException) {
            onRequestError((BaseException) throwable);
        } else {
            onRequestError(BaseException.toUnexpectedError(throwable));
        }
    }
    public abstract void onRequestError(BaseException error);
}
