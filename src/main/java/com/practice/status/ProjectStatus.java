package com.practice.status;

/**
 * @author hzq
 * @date 2019/12/13
 */
public enum ProjectStatus {

    /**
     * 状态 1创建 2审核 3驳回 4通过
     */
    Create(1),
    UnReview(2),
    Reject(3),
    Success(4);

    private final int status;

    ProjectStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public static boolean isIllegal(int status) {
        for (ProjectStatus projectStatus : ProjectStatus.values()) {
            if (projectStatus.status == status) {
                return true;
            }
        }
        return false;
    }
}
