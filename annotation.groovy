@interface SomeAnnotation01 {}

@interface SomeAnnotation02 {
	    String value()                          
}
@interface SomeAnnotation03 {
	    String value() default 'something'      
}
@interface SomeAnnotation04 {
	    int step()                              
}
@interface SomeAnnotation05 {
	    Class appliesTo()                       
}
@interface SomeAnnotation06 {}
@interface SomeAnnotations {
	    SomeAnnotation[] value()                
}

enum DayOfWeek { mon, tue, wed, thu, fri, sat, sun }

@interface Scheduled {
	    DayOfWeek dayOfWeek()                   
}

assert 1 == 1
