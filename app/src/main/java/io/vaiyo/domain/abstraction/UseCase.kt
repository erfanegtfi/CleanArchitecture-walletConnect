package io.vaiyo.domain.abstraction

interface UseCase<P,R> {
 fun action(param: P):R
}