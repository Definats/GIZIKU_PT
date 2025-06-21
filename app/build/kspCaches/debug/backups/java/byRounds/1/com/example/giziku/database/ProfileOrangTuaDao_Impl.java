package com.example.giziku.database;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.giziku.model.ProfileOrangTua;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class ProfileOrangTuaDao_Impl implements ProfileOrangTuaDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ProfileOrangTua> __insertionAdapterOfProfileOrangTua;

  private final EntityDeletionOrUpdateAdapter<ProfileOrangTua> __updateAdapterOfProfileOrangTua;

  private final SharedSQLiteStatement __preparedStmtOfDeleteProfileByUserId;

  public ProfileOrangTuaDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfProfileOrangTua = new EntityInsertionAdapter<ProfileOrangTua>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `profileOrangTua` (`userOrangTuaId`,`username`,`email`,`phone`,`tanggalLahir`,`jenisKelamin`,`fileUri`) VALUES (?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ProfileOrangTua entity) {
        statement.bindLong(1, entity.getUserOrangTuaId());
        statement.bindString(2, entity.getUsername());
        statement.bindString(3, entity.getEmail());
        statement.bindString(4, entity.getPhone());
        statement.bindString(5, entity.getTanggalLahir());
        statement.bindString(6, entity.getJenisKelamin());
        statement.bindString(7, entity.getFileUri());
      }
    };
    this.__updateAdapterOfProfileOrangTua = new EntityDeletionOrUpdateAdapter<ProfileOrangTua>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `profileOrangTua` SET `userOrangTuaId` = ?,`username` = ?,`email` = ?,`phone` = ?,`tanggalLahir` = ?,`jenisKelamin` = ?,`fileUri` = ? WHERE `userOrangTuaId` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ProfileOrangTua entity) {
        statement.bindLong(1, entity.getUserOrangTuaId());
        statement.bindString(2, entity.getUsername());
        statement.bindString(3, entity.getEmail());
        statement.bindString(4, entity.getPhone());
        statement.bindString(5, entity.getTanggalLahir());
        statement.bindString(6, entity.getJenisKelamin());
        statement.bindString(7, entity.getFileUri());
        statement.bindLong(8, entity.getUserOrangTuaId());
      }
    };
    this.__preparedStmtOfDeleteProfileByUserId = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM profileOrangTua WHERE userOrangTuaId = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertProfile(final ProfileOrangTua profile,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfProfileOrangTua.insertAndReturnId(profile);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateProfile(final ProfileOrangTua profile,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfProfileOrangTua.handle(profile);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteProfileByUserId(final long userId,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteProfileByUserId.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, userId);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteProfileByUserId.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object getProfileByUserId(final long userId,
      final Continuation<? super ProfileOrangTua> $completion) {
    final String _sql = "SELECT * FROM profileOrangTua WHERE userOrangTuaId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, userId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<ProfileOrangTua>() {
      @Override
      @Nullable
      public ProfileOrangTua call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfUserOrangTuaId = CursorUtil.getColumnIndexOrThrow(_cursor, "userOrangTuaId");
          final int _cursorIndexOfUsername = CursorUtil.getColumnIndexOrThrow(_cursor, "username");
          final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
          final int _cursorIndexOfPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "phone");
          final int _cursorIndexOfTanggalLahir = CursorUtil.getColumnIndexOrThrow(_cursor, "tanggalLahir");
          final int _cursorIndexOfJenisKelamin = CursorUtil.getColumnIndexOrThrow(_cursor, "jenisKelamin");
          final int _cursorIndexOfFileUri = CursorUtil.getColumnIndexOrThrow(_cursor, "fileUri");
          final ProfileOrangTua _result;
          if (_cursor.moveToFirst()) {
            final long _tmpUserOrangTuaId;
            _tmpUserOrangTuaId = _cursor.getLong(_cursorIndexOfUserOrangTuaId);
            final String _tmpUsername;
            _tmpUsername = _cursor.getString(_cursorIndexOfUsername);
            final String _tmpEmail;
            _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
            final String _tmpPhone;
            _tmpPhone = _cursor.getString(_cursorIndexOfPhone);
            final String _tmpTanggalLahir;
            _tmpTanggalLahir = _cursor.getString(_cursorIndexOfTanggalLahir);
            final String _tmpJenisKelamin;
            _tmpJenisKelamin = _cursor.getString(_cursorIndexOfJenisKelamin);
            final String _tmpFileUri;
            _tmpFileUri = _cursor.getString(_cursorIndexOfFileUri);
            _result = new ProfileOrangTua(_tmpUserOrangTuaId,_tmpUsername,_tmpEmail,_tmpPhone,_tmpTanggalLahir,_tmpJenisKelamin,_tmpFileUri);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
