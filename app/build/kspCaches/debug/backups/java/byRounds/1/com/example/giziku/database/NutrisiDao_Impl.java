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
import com.example.giziku.util.InstantConverter;
import com.example.giziku.util.Nutrisi;
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
public final class NutrisiDao_Impl implements NutrisiDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Nutrisi> __insertionAdapterOfNutrisi;

  public NutrisiDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfNutrisi = new EntityInsertionAdapter<Nutrisi>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `nutrisi` (`id`,`kalori`,`protein`,`karbohidrat`,`lemak`,`serat`,`gula`,`natrium`,`kolesterol`,`vitamin`,`userId`,`createdAt`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Nutrisi entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getKalori());
        statement.bindLong(3, entity.getProtein());
        statement.bindLong(4, entity.getKarbohidrat());
        statement.bindLong(5, entity.getLemak());
        statement.bindLong(6, entity.getSerat());
        statement.bindLong(7, entity.getGula());
        statement.bindLong(8, entity.getNatrium());
        statement.bindLong(9, entity.getKolesterol());
        statement.bindLong(10, entity.getVitamin());
        statement.bindLong(11, entity.getUserId());
        final Long _tmp = InstantConverter.INSTANCE.fromInstant(entity.getCreatedAt());
        if (_tmp == null) {
          statement.bindNull(12);
        } else {
          statement.bindLong(12, _tmp);
        }
      }
    };
  }

  @Override
  public Object insertNutrisi(final Nutrisi nutrisi, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfNutrisi.insert(nutrisi);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<Nutrisi>> getNutrisiByUserID(final long userId) {
    final String _sql = "SELECT * FROM nutrisi WHERE userId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, userId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"nutrisi"}, new Callable<List<Nutrisi>>() {
      @Override
      @NonNull
      public List<Nutrisi> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfKalori = CursorUtil.getColumnIndexOrThrow(_cursor, "kalori");
          final int _cursorIndexOfProtein = CursorUtil.getColumnIndexOrThrow(_cursor, "protein");
          final int _cursorIndexOfKarbohidrat = CursorUtil.getColumnIndexOrThrow(_cursor, "karbohidrat");
          final int _cursorIndexOfLemak = CursorUtil.getColumnIndexOrThrow(_cursor, "lemak");
          final int _cursorIndexOfSerat = CursorUtil.getColumnIndexOrThrow(_cursor, "serat");
          final int _cursorIndexOfGula = CursorUtil.getColumnIndexOrThrow(_cursor, "gula");
          final int _cursorIndexOfNatrium = CursorUtil.getColumnIndexOrThrow(_cursor, "natrium");
          final int _cursorIndexOfKolesterol = CursorUtil.getColumnIndexOrThrow(_cursor, "kolesterol");
          final int _cursorIndexOfVitamin = CursorUtil.getColumnIndexOrThrow(_cursor, "vitamin");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<Nutrisi> _result = new ArrayList<Nutrisi>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Nutrisi _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final int _tmpKalori;
            _tmpKalori = _cursor.getInt(_cursorIndexOfKalori);
            final int _tmpProtein;
            _tmpProtein = _cursor.getInt(_cursorIndexOfProtein);
            final int _tmpKarbohidrat;
            _tmpKarbohidrat = _cursor.getInt(_cursorIndexOfKarbohidrat);
            final int _tmpLemak;
            _tmpLemak = _cursor.getInt(_cursorIndexOfLemak);
            final int _tmpSerat;
            _tmpSerat = _cursor.getInt(_cursorIndexOfSerat);
            final int _tmpGula;
            _tmpGula = _cursor.getInt(_cursorIndexOfGula);
            final int _tmpNatrium;
            _tmpNatrium = _cursor.getInt(_cursorIndexOfNatrium);
            final int _tmpKolesterol;
            _tmpKolesterol = _cursor.getInt(_cursorIndexOfKolesterol);
            final int _tmpVitamin;
            _tmpVitamin = _cursor.getInt(_cursorIndexOfVitamin);
            final long _tmpUserId;
            _tmpUserId = _cursor.getLong(_cursorIndexOfUserId);
            final Instant _tmpCreatedAt;
            final Long _tmp;
            if (_cursor.isNull(_cursorIndexOfCreatedAt)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(_cursorIndexOfCreatedAt);
            }
            final Instant _tmp_1 = InstantConverter.INSTANCE.toInstant(_tmp);
            if (_tmp_1 == null) {
              throw new IllegalStateException("Expected NON-NULL 'kotlinx.datetime.Instant', but it was NULL.");
            } else {
              _tmpCreatedAt = _tmp_1;
            }
            _item = new Nutrisi(_tmpId,_tmpKalori,_tmpProtein,_tmpKarbohidrat,_tmpLemak,_tmpSerat,_tmpGula,_tmpNatrium,_tmpKolesterol,_tmpVitamin,_tmpUserId,_tmpCreatedAt);
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

  @Override
  public Nutrisi getNutrisiByID(final long id) {
    final String _sql = "SELECT * FROM nutrisi WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfKalori = CursorUtil.getColumnIndexOrThrow(_cursor, "kalori");
      final int _cursorIndexOfProtein = CursorUtil.getColumnIndexOrThrow(_cursor, "protein");
      final int _cursorIndexOfKarbohidrat = CursorUtil.getColumnIndexOrThrow(_cursor, "karbohidrat");
      final int _cursorIndexOfLemak = CursorUtil.getColumnIndexOrThrow(_cursor, "lemak");
      final int _cursorIndexOfSerat = CursorUtil.getColumnIndexOrThrow(_cursor, "serat");
      final int _cursorIndexOfGula = CursorUtil.getColumnIndexOrThrow(_cursor, "gula");
      final int _cursorIndexOfNatrium = CursorUtil.getColumnIndexOrThrow(_cursor, "natrium");
      final int _cursorIndexOfKolesterol = CursorUtil.getColumnIndexOrThrow(_cursor, "kolesterol");
      final int _cursorIndexOfVitamin = CursorUtil.getColumnIndexOrThrow(_cursor, "vitamin");
      final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
      final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
      final Nutrisi _result;
      if (_cursor.moveToFirst()) {
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        final int _tmpKalori;
        _tmpKalori = _cursor.getInt(_cursorIndexOfKalori);
        final int _tmpProtein;
        _tmpProtein = _cursor.getInt(_cursorIndexOfProtein);
        final int _tmpKarbohidrat;
        _tmpKarbohidrat = _cursor.getInt(_cursorIndexOfKarbohidrat);
        final int _tmpLemak;
        _tmpLemak = _cursor.getInt(_cursorIndexOfLemak);
        final int _tmpSerat;
        _tmpSerat = _cursor.getInt(_cursorIndexOfSerat);
        final int _tmpGula;
        _tmpGula = _cursor.getInt(_cursorIndexOfGula);
        final int _tmpNatrium;
        _tmpNatrium = _cursor.getInt(_cursorIndexOfNatrium);
        final int _tmpKolesterol;
        _tmpKolesterol = _cursor.getInt(_cursorIndexOfKolesterol);
        final int _tmpVitamin;
        _tmpVitamin = _cursor.getInt(_cursorIndexOfVitamin);
        final long _tmpUserId;
        _tmpUserId = _cursor.getLong(_cursorIndexOfUserId);
        final Instant _tmpCreatedAt;
        final Long _tmp;
        if (_cursor.isNull(_cursorIndexOfCreatedAt)) {
          _tmp = null;
        } else {
          _tmp = _cursor.getLong(_cursorIndexOfCreatedAt);
        }
        final Instant _tmp_1 = InstantConverter.INSTANCE.toInstant(_tmp);
        if (_tmp_1 == null) {
          throw new IllegalStateException("Expected NON-NULL 'kotlinx.datetime.Instant', but it was NULL.");
        } else {
          _tmpCreatedAt = _tmp_1;
        }
        _result = new Nutrisi(_tmpId,_tmpKalori,_tmpProtein,_tmpKarbohidrat,_tmpLemak,_tmpSerat,_tmpGula,_tmpNatrium,_tmpKolesterol,_tmpVitamin,_tmpUserId,_tmpCreatedAt);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
