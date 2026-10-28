package org.reference.panel;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import defpackage.k8;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class UpdateApkProvider extends ContentProvider {
    public static final /* synthetic */ int a = 0;

    @Override // android.content.ContentProvider
    public final int delete(Uri uri, String str, String[] strArr) {
        uri.getClass();
        return 0;
    }

    @Override // android.content.ContentProvider
    public final String getType(Uri uri) {
        uri.getClass();
        return "application/vnd.android.package-archive";
    }

    @Override // android.content.ContentProvider
    public final Uri insert(Uri uri, ContentValues contentValues) {
        uri.getClass();
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContentProvider
    public final boolean onCreate() {
        return true;
    }

    @Override // android.content.ContentProvider
    public final ParcelFileDescriptor openFile(Uri uri, String str) throws FileNotFoundException {
        uri.getClass();
        str.getClass();
        if (!str.equals("r")) {
            throw new SecurityException("read only");
        }
        Context context = getContext();
        if (context == null) {
            throw new IllegalStateException();
        }
        File file = new File(context.getCacheDir(), "update.apk");
        if (!file.isFile()) {
            throw new FileNotFoundException(uri.toString());
        }
        ParcelFileDescriptor parcelFileDescriptorOpen = ParcelFileDescriptor.open(file, 268435456);
        parcelFileDescriptorOpen.getClass();
        return parcelFileDescriptorOpen;
    }

    @Override // android.content.ContentProvider
    public final Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        uri.getClass();
        Context context = getContext();
        if (context == null) {
            throw new IllegalStateException();
        }
        File file = new File(context.getCacheDir(), "update.apk");
        if (strArr == null) {
            strArr = new String[]{"_display_name", "_size"};
        }
        MatrixCursor matrixCursor = new MatrixCursor(strArr, 1);
        ArrayList arrayList = new ArrayList(strArr.length);
        for (String str3 : strArr) {
            arrayList.add(k8.e(str3, "_display_name") ? "ReferencePanel.apk" : k8.e(str3, "_size") ? Long.valueOf(file.length()) : null);
        }
        matrixCursor.addRow(arrayList);
        return matrixCursor;
    }

    @Override // android.content.ContentProvider
    public final int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        uri.getClass();
        return 0;
    }
}
