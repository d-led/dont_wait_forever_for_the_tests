import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

// system under test: should tick once after 5 seconds
def observable = Observable
    .just(1)
    .delay(5, TimeUnit.SECONDS, Schedulers.computation())

// the desired side-effect
observable.subscribe { println 'ah, OK, done! Or not?' }

// the test
Observable
    .interval(1, TimeUnit.SECONDS, Schedulers.computation())
    .subscribe { println 'still waiting...' }

println 'starting to wait for the test to complete ...'

// wait for completion
observable.blockingLast()
