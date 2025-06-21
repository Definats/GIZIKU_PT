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
import com.example.giziku.model.ProfileTenagaMedis;
import java.lang.Class;
import java.lang.Exception;
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
public final class ProfileTenagaMedisDao_Impl implements ProfileTenagaMedisDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ProfileTenagaMedis> __insertionAdapterOfProfileTenagaMedis;

  private final EntityDeletionOrUpdateAdapter<ProfileTenagaMedis> __updateAdapterOfProfileTenagaMedis;

  private final SharedSQLiteStatement __preparedStmtOfDeleteProfileByUserId;

  public ProfileTenagaMedisDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfProfileTenagaMedis = new EntityInsertionAdapter<ProfileTenagaMedis>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `profileTenagaMedis` (`userTenagaMedisId`,`username`,`email`,`phone`,`spesialis`,`jenisKelamin`,`fileUri`) VALUES (?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ProfileTenagaMedis entity) {
        statement.bindLong(1, entity.getUserTenagaMedisId());
        statement.bindString(2, entity.getUsername());
        statement.bindString(3, entity.getEmail());
        statement.bindString(4, entity.getPhone());
        statement.bindString(5, entity.getSpesialis());
        statement.bindString(6, entity.getJenisKelamin());
        statement.bindString(7, entity.getFileUri());
      }
    };
    this.__updateAdapterOfProfileTenagaMedis = new EntityDeletionOrUpdateAdapter<ProfileTenagaMedis>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `profileTenagaMedis` SET `userTenagaMedisId` = ?,`username` = ?,`email` = ?,`phone` = ?,`spesialis` = ?,`jenisKelamin` = ?,`fileUri` = ? WHERE `userTenagaMedisId` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ProfileTenagaMedis entity) {
        statement.bindLong(1, entity.getUserTenagaMedisId());
        statement.bindString(2, entity.getUsername());
        statement.bindString(3, entity.getEmail());
        statement.bindString(4, entity.getPhone());
        statement.bindString(5, entity.getSpesialis());
        statement.bindString(6, entity.getJenisKelamin());
        statement.bindString(7, entity.getFileUri());
        statement.bindLong(8, entity.getUserTenagaMedisId());
      }
    };
    this.__preparedStmtOfDeleteProfileByUserId = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM profileTenagaMedis WHERE userTenagaMedisId = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertProfile(final ProfileTenagaMedis profile,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfProfileTenagaMedis.insert(profile);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateProfile(final ProfileTenagaMedis profile,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfProfileTenagaMedis.handle(profile);
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
      final Continuation<? super ProfileTenagaMedis> $completion) {
    final String _sql = "SELECT * FROM profileTenagaMedis WHERE userTenagaMedisId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, userId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<ProfileTenagaMedis>() {
      @Override
      @Nullable
      public ProfileTenagaMedis call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfUserTenagaMedisId = CursorUtil.getColumnIndexOrThrow(_cursor, "userTenagaMedisId");
          final int _cursorIndexOfUsername = CursorUtil.getColumnIndexOrThrow(_cursor, "username");
          final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
          final int _cursorIndexOfPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "phone");
          final int _cursorIndexOfSpesialis = CursorUtil.getColumnIndexOrThrow(_cursor, "spesialis");
          final int _cursorIndexOfJenisKelamin = CursorUtil.getColumnIndexOrThrow(_cursor, "jenisKelamin");
          final int _cursorIndexOfFileUri = CursorUtil.getColumnIndexOrThrow(_cursor, "fileUri");
          final ProfileTenagaMedis _result;
          if (_cursor.moveToFirst()) {
            final long _tmpUserTenagaMedisId;
            _tmpUserTenagaMedisId = _cursor.getLong(_cursorIndexOfUserTenagaMedisId);
            final String _tmpUsername;
            _tmpUsername = _cursor.getString(_cursorIndexOfUsername);
            final String _tmpEmail;
            _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
            final String _tmpPhone;
            _tmpPhone = _cursor.getString(_cursorIndexOfPhone);
            final String _tmpSpesialis;
            _tmpSpesialis = _cursor.getString(_cursorIndexOfSpesialis);
            final String _tmpJenisKelamin;
            _tmpJenisKelamin = _cursor.getString(_cursorIndexOfJenisKelamin);
            final String _tmpFileUri;
            _tmpFileUri = _cursor.getString(_cursorIndexOfFileUri);
            _result = new ProfileTenagaMedis(_tmpUserTenagaMedisId,_tmpUsername,_tmpEmail,_tmpPhone,_tmpSpesialis,_tmpJenisKelamin,_tmpFileUri);
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
