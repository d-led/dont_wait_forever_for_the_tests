import spock.lang.Specification

import rx.Observable
import rx.schedulers.TestScheduler
import java.util.concurrent.TimeUnit


class DontWaitForever extends Specification {
    def "why wait?"() {
        setup:
            def scheduler = new TestScheduler()

            // system under test: will tick once after a hundred days
            def observable = Observable.just(1).delay(100, TimeUnit.DAYS, scheduler)
            def done = false

        when:
            observable.subscribe {
                done = true
            }

            // still in the initial state
            done == false

        and:
            scheduler.advanceTimeBy 100, TimeUnit.DAYS

        then:
            done == true
    }
}
