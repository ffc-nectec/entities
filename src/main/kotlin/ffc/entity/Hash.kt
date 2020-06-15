package ffc.entity

import org.mindrot.jbcrypt.BCrypt
import java.nio.charset.StandardCharsets.UTF_8
import java.security.MessageDigest

/**
 * ใช้สำหรับสร้างข้อความลับ
 * One way hash
 * เพื่อป้องกันการอ่านข้อมูล
 */
class Hash {
    /**
     * สำหรับใช้งานพื้นฐาน
     * ถ้า value เป็นค่าเดิม hash ก็จะได้ค่าเดิม
     * @param value ข้อความหรือรหัสผ่าน
     * @return hash string
     */
    fun simple(value: String): String {
        val digest = MessageDigest.getInstance("SHA-256")
        val encodedHash = digest.digest(value.toByteArray(UTF_8))
        return encodedHash.toHex() ?: ""
    }

    /**
     * สำหรับการใช้งานที่ต้องการเป็นความลับสูง
     * ใช้ทรัพยาการเครื่องสูง แต่ก็มีความปลอดภัยสูง
     * เวลาตรวจสอบจะต้องใช้ #bestCheck
     * @param plain ข้อความหรือรหัสผ่าน
     * @return hash string
     */
    fun best(plain: String): String {
        val salt = BCrypt.gensalt(10)
        return BCrypt.hashpw(plain, salt)
    }

    /**
     * ใช้สำหรับตรวจสอบ hash ที่สร้างจาก @see best
     * @param plain ข้อความที่ต้องการตรวจสอบ
     * @param hash hash string
     * @return ผลการตรวจสอบว่าตรงกันหรือไม่
     */
    fun bestCheck(plain: String, hash: String): Boolean {
        return BCrypt.checkpw(plain, hash)
    }

    private fun ByteArray.toHex(): String? {
        val hexString = StringBuffer()
        for (i in this.indices) {
            val hex = Integer.toHexString(0xff and this[i].toInt())
            if (hex.length == 1) hexString.append('0')
            hexString.append(hex)
        }
        return hexString.toString()
    }
}
