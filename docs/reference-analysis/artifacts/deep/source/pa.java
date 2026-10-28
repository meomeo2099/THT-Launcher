package defpackage;

import android.graphics.ImageDecoder;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class pa implements ImageDecoder.OnHeaderDecodedListener {
    public final /* synthetic */ int a;

    public /* synthetic */ pa(int i) {
        this.a = i;
    }

    @Override // android.graphics.ImageDecoder.OnHeaderDecodedListener
    public final void onHeaderDecoded(ImageDecoder imageDecoder, ImageDecoder.ImageInfo imageInfo, ImageDecoder.Source source) {
        switch (this.a) {
            case 0:
                int i = MainActivity.E0;
                imageDecoder.getClass();
                imageInfo.getClass();
                source.getClass();
                int width = imageInfo.getSize().getWidth();
                int height = imageInfo.getSize().getHeight();
                if (width > 0 && height > 0) {
                    double dMin = Math.min(1.0d, 1600.0d / ((double) Math.max(width, height)));
                    imageDecoder.setTargetSize(Integer.valueOf(Math.max(1, (int) (((double) width) * dMin))).intValue(), Integer.valueOf(Math.max(1, (int) (((double) height) * dMin))).intValue());
                    imageDecoder.setAllocator(1);
                } else {
                    w.a("Failed requirement.");
                }
                break;
            case 1:
                imageDecoder.getClass();
                imageInfo.getClass();
                source.getClass();
                int width2 = imageInfo.getSize().getWidth();
                int height2 = imageInfo.getSize().getHeight();
                if (width2 > 0 && height2 > 0) {
                    double dMin2 = Math.min(1.0d, 512.0d / ((double) Math.max(width2, height2)));
                    imageDecoder.setTargetSize(Integer.valueOf(Math.max(1, (int) (((double) width2) * dMin2))).intValue(), Integer.valueOf(Math.max(1, (int) (((double) height2) * dMin2))).intValue());
                    imageDecoder.setAllocator(1);
                } else {
                    w.a("Failed requirement.");
                }
                break;
            default:
                imageDecoder.getClass();
                imageInfo.getClass();
                source.getClass();
                int width3 = imageInfo.getSize().getWidth();
                int height3 = imageInfo.getSize().getHeight();
                if (width3 > 0 && height3 > 0) {
                    double dMin3 = Math.min(1.0d, 1600.0d / ((double) Math.max(width3, height3)));
                    imageDecoder.setTargetSize(Integer.valueOf(Math.max(1, (int) (((double) width3) * dMin3))).intValue(), Integer.valueOf(Math.max(1, (int) (((double) height3) * dMin3))).intValue());
                    imageDecoder.setAllocator(1);
                } else {
                    w.a("Failed requirement.");
                }
                break;
        }
    }
}
