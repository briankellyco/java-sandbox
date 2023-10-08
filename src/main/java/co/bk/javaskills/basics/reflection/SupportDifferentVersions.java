//package co.bk.javabasics.main.reflection;
//
//import java.io.IOException;
//import java.lang.reflect.Constructor;
//import java.lang.reflect.Method;
//
//import oracle.spatial.support.ShapefileReaderJGeom;
//
///**
// * BUSINESS PROBLEM & SOLUTION:
// * A mapping product I worked on used an Oracle library to import map information (roads, lakes etc).
// * I changed the behaviour of the image loading class to check for the available driver version.
// *
// * DETAILS:
// * Two technical combinations had to be supported:
// * 1. JDK 1.4 & Oracle 10G library (some customers did not want to upgrade).
// * 2. JDK 1.5+ & Oracle 11G library.
// *
// * The Oracle driver constructor/method signatures changed between the 10G and 11G
// * versions. As a result image loader tool needed to detect which driver was on the
// * classpath in order to initialise and call the method correctly.
// *
// * The code snippets in the class below illustrate how that was achieved.
// *
// * NOTE: This maven project requires an assembly file to be configured to build with
// * both 10G and 11G jars. Only the 11G jar is being built with the project currently.
// *
// * SETUP STEPS TO RUN THIS:
// * 1. Install Oracle jar to local maven repo. JAR found in "src/libs". Change path to file as required.
// * 		mvn install:install-file -Dfile=/home/brian/Documents/CODE/__codeskills/javaSkills/src/libs/sdoutl-11.2.0.jar -DgroupId=com.oracle -DartifactId=sdoutl -Dversion=11.2.0 -Dpackaging=jar
// * 		mvn install:install-file -Dfile=/home/brian/Documents/CODE/__codeskills/javaSkills/src/libs/sdoutl-1.0.jar -DgroupId=com.oracle -DartifactId=sdoutl -Dversion=1.0 -Dpackaging=jar
// * 		mvn install:install-file -Dfile=/home/brian/Documents/CODE/__codeskills/javaSkills/src/libs/sdoapi-11.2.0.jar -DgroupId=com.oracle -DartifactId=sdoapi -Dversion=11.2.0 -Dpackaging=jar
// * 2. Differences between the APIs can be viewed with:
// * 		11G --> javap -classpath sdoutl-11.2.0.jar oracle.spatial.support.ShapefileReaderJGeom
// * 		10G --> javap -classpath sdoutl-1.0.jar oracle.spatial.support.ShapefileReaderJGeom
// * 3. Two different versions of a jar required in a maven project? How that problem was solved:
// * 		http://stackoverflow.com/questions/4312553/maven-assembly-add-different-version-of-the-same-artifact
// *
// * @author brian kelly
// */
//public class SupportDifferentVersions {
//
//
//	public static void main(String[] args) {
//		boolean bOracle11G = ShapeLoaderDummyClass.is11gAPI();
//		System.out.println("Class checked for version of jar deployed. Is 11G? " + bOracle11G);
//	}
//
//    public static class ShapeLoaderDummyClass {
//
//    	private static boolean is11GAPI  = false;
//
//		private static boolean is11GTest = false;
//
//    	static {
//    		is11GAPI = is11gAPI();
//    	}
//
//    	  /**
//    	   * Method checks if the Oracle 11G Java API (sdoutl-11g.jar) is in use.
//    	   *
//    	   * @return true if 11GAPI
//    	   */
//    	public synchronized static boolean is11gAPI() {
//
//    	    if (!is11GTest) {
//    	      try
//    	        {
//    	    	//Check if the named method with a single int argument exists. Exception thrown if not found
//    	    	//The getGeometryBytes method has two arguments in the 10G version
//    	        Method m = ShapefileReaderJGeom.class.getMethod( "getGeometryBytes", new Class[]{int.class} );
//    	        is11GAPI = true;
//    	        }
//    	      catch ( Exception ex )
//    	        {
//    	        is11GAPI = false;
//    	        }
//    	      is11GTest = true;
//    	    }
//    	    return is11GAPI;
//    	}
//
//    	public static void testLoadShapeFile (){
//
//    		try {
//        		ShapefileReaderJGeom shapefileReader = getShapefileReaderJGeom("nameOfShapeFile", 4);
//    		} catch (Exception e) {
//
//    		}
//    	}
//
//    	  /**
//    	   * Instantiates a <CODE>ShapefileReaderJGeom</CODE> class
//    	   *
//    	   * @param shapeFileName the shape file name
//    	   * @param m_dim the dimensions
//    	   * @return the ShapefileReaderJGeom class
//    	   * @throws IOException Signals that an I/O exception has occurred.
//    	   */
//    	private static ShapefileReaderJGeom getShapefileReaderJGeom( String shapeFileName, int m_dim ) throws IOException {
//    		if ( is11gAPI() ) {
//        		try {
//        			Constructor cc = ShapefileReaderJGeom.class.getConstructor( new Class[]{String.class} );
//        			return (ShapefileReaderJGeom) cc.newInstance( new Object[]{shapeFileName} );
//        		} catch (Exception e) {
//        			//10G constructor. Commented out due to maven build not including 10G jar in this project.
//        			//return new ShapefileReaderJGeom( shapeFileName, m_dim );
//        		}
//    		} else {
//    			//10G constructor. Commented out due to maven build not including 10G jar in this project.
//    			//return new ShapefileReaderJGeom( shapeFileName, m_dim );
//    		}
//    		return null;
//    	}
//
//    }
//}
