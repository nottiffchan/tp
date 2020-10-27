package trackitnus.logic.parser;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import trackitnus.commons.core.index.Index;
import trackitnus.commons.util.StringUtil;
import trackitnus.logic.parser.exceptions.ParseException;
import trackitnus.model.commons.Address;
import trackitnus.model.commons.Code;
import trackitnus.model.commons.Name;
import trackitnus.model.contact.Email;
import trackitnus.model.contact.Phone;
import trackitnus.model.lesson.Lesson;
import trackitnus.model.lesson.LessonDateTime;
import trackitnus.model.lesson.LessonWeekday;
import trackitnus.model.lesson.Type;
import trackitnus.model.tag.Tag;
import trackitnus.model.task.Task;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     *
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses a {@code String code} into an {@code Code}.
     *
     * @throws ParseException if the given {@code code} is invalid.
     */
    public static Code parseCode(String code) throws ParseException {
        requireNonNull(code);
        String trimmedCode = code.trim();
        if (!Code.isValidCode(trimmedCode)) {
            throw new ParseException(Code.MESSAGE_CONSTRAINTS);
        }
        return new Code(trimmedCode);
    }

    /**
     * Parses an {@code Optional<String> code} into an {@code Code}.
     *
     * @throws ParseException if the given {@code code} is invalid.
     */
    public static Code parseOptionalCode(Optional<String> code) throws ParseException {
        if (code.isEmpty()) {
            return null;
        }
        String trimmedCode = code.get().trim();
        if (!Task.isValidString(trimmedCode)) {
            return null;
        }
        if (!Code.isValidCode(trimmedCode)) {
            throw new ParseException(Code.MESSAGE_CONSTRAINTS);
        }
        return new Code(trimmedCode);
    }

    /**
     * Parses a {@code String str} into a {@code String}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code str} is invalid.
     */
    public static String parseString(String str) {
        requireNonNull(str);
        return str.trim();
    }

    /**
     * Parses a {@code String date} into a {@code LocalDate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code date} is invalid.
     */
    public static LocalDate parseDate(String date) throws ParseException {
        requireNonNull(date);
        String trimmedDate = date.trim();
        try {
            return LocalDate.parse(trimmedDate, Task.FORMATTER);
        } catch (DateTimeParseException e) {
            throw new ParseException(Task.DATE_MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Parses a {@code String weightage} into a {@code double}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code weightage} is invalid.
     */
    public static double parseWeightage(String weightage) throws ParseException {
        requireNonNull(weightage);
        String trimmedWeightage = weightage.trim();
        try {
            return Double.parseDouble(trimmedWeightage);
        } catch (NumberFormatException e) {
            throw new ParseException(Task.WEIGHTAGE_MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Parses a {@code String remark} into a {@code String}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code remark} is invalid.
     */
    public static String parseRemark(Optional<String> remark) {
        if (remark.isEmpty()) {
            return "";
        }
        String trimmedRemark = remark.get().trim();
        return trimmedRemark;
    }

    /**
     * Parses a {@code String type} into a {@code Type}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code type} is invalid.
     */
    public static Type parseType(String type) throws ParseException {
        requireNonNull(type);
        String rawType = type.trim();
        switch (rawType) {
        case "lecture":
            return Type.LEC;
        case "tutorial":
            return Type.TUT;
        case "lab":
            return Type.LAB;
        case "recitation":
            return Type.REC;
        case "sectional":
            return Type.SEC;
        default:
            throw new ParseException(Lesson.TYPE_MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Parses a {@code String weekday} into a {@code LessonWeekday}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code weekday} is invalid.
     */
    private static LessonWeekday parseLessonWeekday(String weekday) throws ParseException {
        requireNonNull(weekday);
        String raw = weekday.trim().toLowerCase();
        switch (raw) {
        case "sun":
            return LessonWeekday.Sun;
        case "mon":
            return LessonWeekday.Mon;
        case "tue":
            return LessonWeekday.Tue;
        case "wed":
            return LessonWeekday.Wed;
        case "thu":
            return LessonWeekday.Thu;
        case "fri":
            return LessonWeekday.Fri;
        case "sat":
            return LessonWeekday.Sat;
        default:
            throw new ParseException(Lesson.DATE_MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Parses a {@code String date} into a {@code LessonDateTime}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code date} is invalid.
     */
    public static LessonDateTime parseLessonDateTime(String date) throws ParseException {
        requireNonNull(date);
        String trimmedDate = date.trim();
        try {
            String[] tokens = trimmedDate.split(" ", 2);
            String[] startEndTime = tokens[1].split("-", 2);
            LessonWeekday weekday = parseLessonWeekday(tokens[0]);
            LocalTime startTime = LocalTime.parse(startEndTime[0], LessonDateTime.FORMATTER);
            LocalTime endTime = LocalTime.parse(startEndTime[1], LessonDateTime.FORMATTER);
            if (startTime.compareTo(endTime) > 0) {
                throw new ParseException(Lesson.TIME_MESSAGE_CONSTRAINTS);
            }
            return new LessonDateTime(weekday, startTime, endTime);
        } catch (Exception e) {
            if (e instanceof ParseException) {
                throw e;
            } else {
                throw new ParseException(Lesson.DATE_MESSAGE_CONSTRAINTS);
            }
        }
    }
}
