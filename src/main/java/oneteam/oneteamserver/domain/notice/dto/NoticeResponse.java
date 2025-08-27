package oneteam.oneteamserver.domain.notice.dto;

import oneteam.oneteamserver.domain.notice.Notice;

public record NoticeResponse(
        Long id, String campus, String category, String title,
        java.time.LocalDate date, Boolean isImportant, String link, String attachments
) {
    public static NoticeResponse of(Notice notice) {
        return new NoticeResponse(
                notice.getId(), notice.getCampus(), notice.getCategory(), notice.getTitle(),
                notice.getDate(), notice.getIsImportant(), notice.getLink(), notice.getAttachments()
        );
    }
}
