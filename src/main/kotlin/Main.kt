fun main(args: Array<String>) {

    var result = agoToText(3000 * 60)
    println(result)

    val result2 = calculateCommission(mastercard, 10_000)
    println(result2)

}


//Задача 1

fun agoToText(seconds: Int): String {

    val hour = 60 * 60
    val day = 60 * 60 * 24
    val twoDays = 24 * 60 * 60 * 2
    val threeDays = 24 * 60 * 60 * 3

    var result = when {
        seconds > 0 && seconds <= 60 -> "был(а) в сети только что"
        seconds >= 61 && seconds <= hour -> minutesAgo(seconds)
        seconds >= hour + 1 && seconds <= day -> hoursAgo(seconds)
        seconds >= day + 1 && seconds <= 24 * 60 * 60 * 2 -> "был(а) в сети вчера"
        seconds >= twoDays + 1 && seconds <= threeDays -> "был(а) в сети позавчера"
        else -> "был(а) в сети давно"
    }

    return result
}

fun minutesAgo(seconds: Int): String {

    val calcMinutes = seconds / 60
    val preLast = calcMinutes % 100 / 10
    val remainderOfDevision = calcMinutes % 10

    val result = when {
        remainderOfDevision == 1 && preLast != 1 -> "был(а) в сети $calcMinutes минуту назад"
        (remainderOfDevision == 2 || remainderOfDevision == 3 || remainderOfDevision == 4) && preLast != 1 -> "был(а) в сети $calcMinutes минуты назад"
        else -> "был(а) в сети $calcMinutes минут назад"
    }

    return result

//    when (seconds) {
//        60 -> return "был(а) в сети минуту назад"
//        60 * 2 -> return "был(а) в сети ${seconds / 60} минуты назад"
//        60 * 5 -> return "был(а) в сети ${seconds / 60} минут назад"
//        60 * 11 -> return "был(а) в сети ${seconds / 60} минут назад"
//        60 * 21 -> return "был(а) в сети ${seconds / 60} минуту назад"
//        60 * 25 -> return "был(а) в сети ${seconds / 60} минут назад"
//    }
//    return "неизвестно"

}

fun hoursAgo(seconds: Int): String {

    val calcHours = seconds / (60 * 60)
    val preLast = calcHours % 100 / 10
    val remainderOfDevision = calcHours % 10

    println("prelast $preLast")
    println("remainder $remainderOfDevision")

    val result = when {
        remainderOfDevision == 1 && preLast != 1 -> "был(а) в сети час назад"
        (remainderOfDevision == 2 || remainderOfDevision == 3 || remainderOfDevision == 4) && preLast != 1 -> "был(а) в сети $calcHours часа назад"
        else -> "был(а) в сети $calcHours часов назад"
    }

//    when (seconds) {
//        60 * 60 -> return "был(а) в сети час назад"
//        60 * 60 * 2 -> return "был(а) в сети ${seconds / (60 * 60)} часа назад"
//        60 * 60 * 5 -> return "был(а) в сети ${seconds / (60 * 60)} часов назад"
//        60 * 60 * 11 -> return "был(а) в сети ${seconds / (60 * 60)} часов назад"
//        60 * 60 * 21 -> return "был(а) в сети ${seconds / (60 * 60)} час назад"
//        60 * 60 * 25 -> return "был(а) в сети ${seconds / (60 * 60)} часов назад"
//    }

    return result
}


//Задача 2

val maestro = "MAESTRO"
val mastercard = "MASTERCARD"
val visa = "VISA"
val mir = "MIR"
val vkPay = "VKPAY"
val maxTransferSumWithCardPerDay = 150_000
val maxTransferSumWithVkPayPerDay = 15_000
val maxTransferSumWithCardPerMonth = 1_200_000
val maxTransferSumWithVkPayPerMonth = 40_000

fun calculateCommission(cardAccountType: String, thisTransferSum: Int, previousTransfersSum: Int = 0): Int {

    val commissionSum: Int = (thisTransferSum * 0.75 / 100).toInt()

    val result = when (cardAccountType) {
        maestro -> when {
            previousTransfersSum > maxTransferSumWithCardPerMonth -> -1
            thisTransferSum > maxTransferSumWithCardPerDay -> -1
            else -> thisTransferSum
        }

        mastercard -> when {
            previousTransfersSum > maxTransferSumWithCardPerMonth -> -1
            thisTransferSum > maxTransferSumWithCardPerDay -> -1
            else -> thisTransferSum
        }

        visa -> when {
            previousTransfersSum > maxTransferSumWithCardPerMonth -> -1
            thisTransferSum > maxTransferSumWithCardPerDay -> -1
            else -> when {
                thisTransferSum >= 35 -> thisTransferSum + commissionSum
                else -> -1
            }
        }

        mir -> when {
            previousTransfersSum > maxTransferSumWithCardPerMonth -> -1
            thisTransferSum > maxTransferSumWithCardPerDay -> -1
            else -> when {
                thisTransferSum >= 35 -> thisTransferSum + commissionSum
                else -> -1
            }
        }

        vkPay -> when {
            previousTransfersSum > maxTransferSumWithVkPayPerMonth -> -1
            thisTransferSum > maxTransferSumWithVkPayPerDay -> -1
            else -> thisTransferSum
        }

        else -> -1
    }

//    val result = when (cardAccountType) {
//        maestro -> when {
//            previousTransfersSum > maxTransferSumWithCardPerMonth -> "Месячный лимит перевода по карте превышен"
//            thisTransferSum > maxTransferSumWithCardPerDay -> "Суточный лимит перевода по карте превышен"
//            else -> "Сумма перевода ${thisTransferSum} рублей. Комиссии нет"
//        }
//        mastercard -> when {
//            previousTransfersSum > maxTransferSumWithCardPerMonth -> "Месячный лимит перевода по карте превышен"
//            thisTransferSum > maxTransferSumWithCardPerDay -> "Суточный лимит перевода по карте превышен"
//            else -> "Сумма перевода ${thisTransferSum} рублей. Комиссии нет"
//        }
//        visa -> when {
//            previousTransfersSum > maxTransferSumWithCardPerMonth -> "Месячный лимит перевода по карте превышен"
//            thisTransferSum > maxTransferSumWithCardPerDay -> "Суточный лимит перевода по карте превышен"
//            else -> when {
//                thisTransferSum >= 35 -> "Сумма перевода составляет ${thisTransferSum + commissionSum} рублей. Минимальная сумма перевода 35 рублей. Комиссия составляет ${commissionSum} рублей"
//                else -> "Переводы осуществляются от 35 рублей"
//            }
//        }
//        mir -> when {
//            previousTransfersSum > maxTransferSumWithCardPerMonth -> "Месячный лимит перевода по карте превышен"
//            thisTransferSum > maxTransferSumWithCardPerDay -> "Суточный лимит перевода по карте превышен"
//            else -> when {
//                thisTransferSum >= 35 -> "Сумма перевода составляет ${thisTransferSum + commissionSum} рублей. Минимальная сумма перевода 35 рублей. Комиссия составляет ${commissionSum} рублей"
//                else -> "Переводы осуществляются от 35 рублей"
//            }
//        }
//        vkPay -> when {
//            previousTransfersSum > maxTransferSumWithVkPayPerMonth -> "Месячный лимит операций по счету превышен"
//            thisTransferSum > maxTransferSumWithVkPayPerDay -> "Суточный лимит операций по счету превышен"
//            else -> "Сумма операции по счету ${thisTransferSum} рублей. Комиссии нет"
//        }
//        else -> "Переводы временно не осуществляются"
//    }


    return result
}
