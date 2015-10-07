import spock.lang.Specification

import rx.Observable
import rx.schedulers.TestScheduler
import java.util.concurrent.TimeUnit


class DontWaitForever extends Specification {
    def "why wait?"() {
        setup:
        	def scheduler = new TestScheduler()
        	def observable = Observable.just(1).delay(100, TimeUnit.DAYS, scheduler)
        	def done = false

        when:
        	observable.subscribe{ _ ->
        		done = true
        	}

        	done == false

        and:
        	scheduler.advanceTimeBy(100, TimeUnit.DAYS)

        then:
        	done == true
    }
}
