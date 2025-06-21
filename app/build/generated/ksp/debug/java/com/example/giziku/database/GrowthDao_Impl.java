package com.example.giziku.database;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.giziku.model.Growth;
import com.example.giziku.util.InstantConverter;
import java.lang.Class;
import java.lang.Exception;
import java.lang.IllegalStateException;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;
import kotlinx.datetime.Instant;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class GrowthDao_Impl implements GrowthDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Growth> __insertionAdapterOfGrowth;

  public GrowthDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfGrowth = new EntityInsertionAdapter<Growth>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `growth` (`id`,`anakId`,`tanggal`,`tinggiBadan`,`beratBadan`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Growth entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getAnakId());
        final Long _tmp = InstantConverter.INSTANCE.fromInstant(entity.getTanggal());
        if (_tmp == null) {
          statement.bindNull(3);
        } else {
          statement.bindLong(3, _tmp);
        }
        statement.bindString(4, entity.getTinggiBadan());
        statement.bindString(5, entity.getBeratBadan());
      }
    };
  }

  @Override
  public Object insertGrowth(final Growth growth, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfGrowth.insert(growth);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<Growth>> getGrowthByAnakId(final long anakId) {
    final String _sql = "SELECT * FROM growth WHERE anakId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, anakId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"growth"}, new Callable<List<Growth>>() {
      @Override
      @NonNull
      public List<Growth> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfAnakId = CursorUtil.getColumnIndexOrThrow(_cursor, "anakId");
          final int _cursorIndexOfTanggal = CursorUtil.getColumnIndexOrThrow(_cursor, "tanggal");
          final int _cursorIndexOfTinggiBadan = CursorUtil.getColumnIndexOrThrow(_cursor, "tinggiBadan");
          final int _cursorIndexOfBeratBadan = CursorUtil.getColumnIndexOrThrow(_cursor, "beratBadan");
          final List<Growth> _result = new ArrayList<Growth>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Growth _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final long _tmpAnakId;
            _tmpAnakId = _cursor.getLong(_cursorIndexOfAnakId);
            final Instant _tmpTanggal;
            final Long _tmp;
            if (_cursor.isNull(_cursorIndexOfTanggal)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(_cursorIndexOfTanggal);
            }
            final Instant _tmp_1 = InstantConverter.INSTANCE.toInstant(_tmp);
            if (_tmp_1 == null) {
              throw new IllegalStateException("Expected NON-NULL 'kotlinx.datetime.Instant', but it was NULL.");
            } else {
              _tmpTanggal = _tmp_1;
            }
            final String _tmpTinggiBadan;
            _tmpTinggiBadan = _cursor.getString(_cursorIndexOfTinggiBadan);
            final String _tmpBeratBadan;
            _tmpBeratBadan = _cursor.getString(_cursorIndexOfBeratBadan);
            _item = new Growth(_tmpId,_tmpAnakId,_tmpTanggal,_tmpTinggiBadan,_tmpBeratBadan);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
