package co.btk;

import java.io.File;

public class LambdaInterface {

  

    /**
     * SEE
     * https://docs.aws.amazon.com/AWSJavaSDK/latest/javadoc/com/amazonaws/event/ProgressListener.html
     *
     * SAM - Single Abstract Method interfaces
     * https://howtodoinjava.com/java8/lambda-expressions/
     *
     */

    /**
     * Option1 - instantiate interface and provide implementation
     */

    //    private ProgressListener createProgressListener(Transfer transfer)
//    {
//        return new ProgressListener()
//        {
//            private ProgressEventType previousType;
//            private double previousTransferred;
//
//            @Override
//            public synchronized void progressChanged(ProgressEvent progressEvent)
//            {
//                ProgressEventType eventType = progressEvent.getEventType();
//                if (previousType != eventType) {
//                    //log.debug("Upload progress event (%s/%s): %s", host, key, eventType);
//                    previousType = eventType;
//                }
//
//                double transferred = transfer.getProgress().getPercentTransferred();
//                if (transferred >= (previousTransferred + 10.0)) {
//                    //log.debug("Upload percentage (%s/%s): %.0f%%", host, key, transferred);
//                    previousTransferred = transferred;
//                }
//            }
//        };
//    }

    /**
     * Option2 - inline instantiation where method body only required
     */
// PutObjectRequest request = new PutObjectRequest(bucketName, key, new File(pathToFile));

//            request.setGeneralProgressListener(
//                    (com.amazonaws.event.ProgressListener) progressEvent -> {
//        switch (progressEvent.getEventType()) {
//            case TRANSFER_STARTED_EVENT:
//                log.debug("Started uploading object {} into Amazon S3", object);
//                break;
//            case REQUEST_BYTE_TRANSFER_EVENT:
//                log.debug("InProgress uploading object {} into Amazon S3 with bytes transferred {}", object, progressEvent.getBytesTransferred());
//                break;
//            case TRANSFER_COMPLETED_EVENT:
//                log.debug("Completed uploading object {} into Amazon S3", object);
//                break;
//            case TRANSFER_FAILED_EVENT:
//                log.debug("Failed uploading object {} into Amazon S3", object);
//                break;
//            default:
//                break;
//        }
//    });



}
