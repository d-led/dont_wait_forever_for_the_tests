import rx.*
import java.util.concurrent.TimeUnit

def observable = Observable
	.just(1)
	.delay(5, TimeUnit.SECONDS)

observable.subscribe {_ -> println 'ah, OK, done! Or not?'}

Observable
	.interval(1,TimeUnit.SECONDS)
	.subscribe {_ -> println 'still waiting...'}

println 'starting to wait for the test to complete ...'

observable.toBlocking().last()
