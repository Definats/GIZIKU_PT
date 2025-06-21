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
import com.example.giziku.model.AnakEntity;
import com.example.giziku.model.ProfileTenagaPendidikan;
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
public final class ProfileTenagaPendidikanDao_Impl implements ProfileTenagaPendidikanDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ProfileTenagaPendidikan> __insertionAdapterOfProfileTenagaPendidikan;

  private final EntityDeletionOrUpdateAdapter<ProfileTenagaPendidikan> __updateAdapterOfProfileTenagaPendidikan;

  private final SharedSQLiteStatement __preparedStmtOfDeleteProfileByUserId;

  public ProfileTenagaPendidikanDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfProfileTenagaPendidikan = new EntityInsertionAdapter<ProfileTenagaPendidikan>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `profileTenagaPendidikan` (`userTenagaPendidikanId`,`username`,`email`,`phone`,`mataPelajaran`,`jenisKelamin`,`fileUri`) VALUES (?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ProfileTenagaPendidikan entity) {
        statement.bindLong(1, entity.getUserTenagaPendidikanId());
        statement.bindString(2, entity.getUsername());
        statement.bindString(3, entity.getEmail());
        statement.bindString(4, entity.getPhone());
        statement.bindString(5, entity.getMataPelajaran());
        statement.bindString(6, entity.getJenisKelamin());
        statement.bindString(7, entity.getFileUri());
      }
    };
    this.__updateAdapterOfProfileTenagaPendidikan = new EntityDeletionOrUpdateAdapter<ProfileTenagaPendidikan>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `profileTenagaPendidikan` SET `userTenagaPendidikanId` = ?,`username` = ?,`email` = ?,`phone` = ?,`mataPelajaran` = ?,`jenisKelamin` = ?,`fileUri` = ? WHERE `userTenagaPendidikanId` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ProfileTenagaPendidikan entity) {
        statement.bindLong(1, entity.getUserTenagaPendidikanId());
        statement.bindString(2, entity.getUsername());
        statement.bindString(3, entity.getEmail());
        statement.bindString(4, entity.getPhone());
        statement.bindString(5, entity.getMataPelajaran());
        statement.bindString(6, entity.getJenisKelamin());
        statement.bindString(7, entity.getFileUri());
        statement.bindLong(8, entity.getUserTenagaPendidikanId());
      }
    };
    this.__preparedStmtOfDeleteProfileByUserId = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM profileTenagaPendidikan WHERE userTenagaPendidikanId = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertProfile(final ProfileTenagaPendidikan profile,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfProfileTenagaPendidikan.insert(profile);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateProfile(final ProfileTenagaPendidikan profile,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfProfileTenagaPendidikan.handle(profile);
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
      final Continuation<? super ProfileTenagaPendidikan> $completion) {
    final String _sql = "SELECT * FROM profileTenagaPendidikan WHERE userTenagaPendidikanId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, userId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<ProfileTenagaPendidikan>() {
      @Override
      @Nullable
      public ProfileTenagaPendidikan call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfUserTenagaPendidikanId = CursorUtil.getColumnIndexOrThrow(_cursor, "userTenagaPendidikanId");
          final int _cursorIndexOfUsername = CursorUtil.getColumnIndexOrThrow(_cursor, "username");
          final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
          final int _cursorIndexOfPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "phone");
          final int _cursorIndexOfMataPelajaran = CursorUtil.getColumnIndexOrThrow(_cursor, "mataPelajaran");
          final int _cursorIndexOfJenisKelamin = CursorUtil.getColumnIndexOrThrow(_cursor, "jenisKelamin");
          final int _cursorIndexOfFileUri = CursorUtil.getColumnIndexOrThrow(_cursor, "fileUri");
          final ProfileTenagaPendidikan _result;
          if (_cursor.moveToFirst()) {
            final long _tmpUserTenagaPendidikanId;
            _tmpUserTenagaPendidikanId = _cursor.getLong(_cursorIndexOfUserTenagaPendidikanId);
            final String _tmpUsername;
            _tmpUsername = _cursor.getString(_cursorIndexOfUsername);
            final String _tmpEmail;
            _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
            final String _tmpPhone;
            _tmpPhone = _cursor.getString(_cursorIndexOfPhone);
            final String _tmpMataPelajaran;
            _tmpMataPelajaran = _cursor.getString(_cursorIndexOfMataPelajaran);
            final String _tmpJenisKelamin;
            _tmpJenisKelamin = _cursor.getString(_cursorIndexOfJenisKelamin);
            final String _tmpFileUri;
            _tmpFileUri = _cursor.getString(_cursorIndexOfFileUri);
            _result = new ProfileTenagaPendidikan(_tmpUserTenagaPendidikanId,_tmpUsername,_tmpEmail,_tmpPhone,_tmpMataPelajaran,_tmpJenisKelamin,_tmpFileUri);
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

  @Override
  public Object getAnakByKode(final String kode,
      final Continuation<? super AnakEntity> $completion) {
    final String _sql = "SELECT * FROM anak WHERE kodeUnik = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, kode);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<AnakEntity>() {
      @Override
      @Nullable
      public AnakEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfNama = CursorUtil.getColumnIndexOrThrow(_cursor, "nama");
          final int _cursorIndexOfTanggalLahir = CursorUtil.getColumnIndexOrThrow(_cursor, "tanggalLahir");
          final int _cursorIndexOfJenisKelamin = CursorUtil.getColumnIndexOrThrow(_cursor, "jenisKelamin");
          final int _cursorIndexOfBeratBadan = CursorUtil.getColumnIndexOrThrow(_cursor, "beratBadan");
          final int _cursorIndexOfTinggiBadan = CursorUtil.getColumnIndexOrThrow(_cursor, "tinggiBadan");
          final int _cursorIndexOfKodeUnik = CursorUtil.getColumnIndexOrThrow(_cursor, "kodeUnik");
          final int _cursorIndexOfOrangTuaId = CursorUtil.getColumnIndexOrThrow(_cursor, "orangTuaId");
          final int _cursorIndexOfKelas = CursorUtil.getColumnIndexOrThrow(_cursor, "kelas");
          final int _cursorIndexOfFileUri = CursorUtil.getColumnIndexOrThrow(_cursor, "fileUri");
          final AnakEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpNama;
            _tmpNama = _cursor.getString(_cursorIndexOfNama);
            final String _tmpTanggalLahir;
            _tmpTanggalLahir = _cursor.getString(_cursorIndexOfTanggalLahir);
            final String _tmpJenisKelamin;
            _tmpJenisKelamin = _cursor.getString(_cursorIndexOfJenisKelamin);
            final String _tmpBeratBadan;
            _tmpBeratBadan = _cursor.getString(_cursorIndexOfBeratBadan);
            final String _tmpTinggiBadan;
            _tmpTinggiBadan = _cursor.getString(_cursorIndexOfTinggiBadan);
            final String _tmpKodeUnik;
            _tmpKodeUnik = _cursor.getString(_cursorIndexOfKodeUnik);
            final long _tmpOrangTuaId;
            _tmpOrangTuaId = _cursor.getLong(_cursorIndexOfOrangTuaId);
            final String _tmpKelas;
            if (_cursor.isNull(_cursorIndexOfKelas)) {
              _tmpKelas = null;
            } else {
              _tmpKelas = _cursor.getString(_cursorIndexOfKelas);
            }
            final String _tmpFileUri;
            _tmpFileUri = _cursor.getString(_cursorIndexOfFileUri);
            _result = new AnakEntity(_tmpId,_tmpNama,_tmpTanggalLahir,_tmpJenisKelamin,_tmpBeratBadan,_tmpTinggiBadan,_tmpKodeUnik,_tmpOrangTuaId,_tmpKelas,_tmpFileUri);
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
