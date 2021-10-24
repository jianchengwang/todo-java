package opencv.imageSimilarity;

public class MatchImage implements Comparable<MatchImage>{
	private String imageName;

	private String imagePath;

	private double matchRate;

	@Override
	public int compareTo(MatchImage o) {
		if(null == o) {
			return 1;
		}
		double sub = this.matchRate - o.matchRate;
		if(sub == 0) {
			return 0;
		}
		return sub < 0 ? 1 : -1;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public double getMatchRate() {
		return matchRate;
	}

	public void setMatchRate(double matchRate) {
		this.matchRate = matchRate;
	}

	@Override
	public String toString() {
		return "MatchImage [imageName=" + imageName + ", matchRate=" + matchRate + "]";
	}

}
